#! /usr/bin/env python
# -*- coding: utf-8 -*-
#
# Copyright 2020 Alibaba Group Holding Limited.
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#

from collections import defaultdict
import os
import sys

import vineyard

def migrate_to_local(replica, rank, object_id):
    client = vineyard.connect(os.environ['VINEYARD_IPC_SOCKET'])

    # get instance id -> node name
    instances = dict()
    cluster = client.meta
    for instance_id, instance in cluster.items():
        instances[instance_id] = instance['nodename']

    meta = client.get_meta(object_id)
    if not meta.isglobal:
        raise ValueError('Expect a global object, but got %s' % meta.typename)


    nodes = []
    chunks = defaultdict(list)
    for k, item in meta.items():
        if isinstance(vineyard.ObjectMeta):
            nodes.append(instances[item.instance_id])
            chunks[instances[k.instance_id]].append(repr(item.id))
    sorted_chunks = dict()
    totalfrags = 0
    for node, items in chunks.items():
        totalfrags += len(items)
        sorted_chunks[node] = sorted(items)

    nchunks = totalfrags / replica + (0 if totalfrags % replica == 0 else 1)

    cnt = 0
    localfrags = []
    for node in sorted(sorted_chunks.keys()):
        for chunk in sorted_chunks[node]:
            if cnt > nchunks * rank and cnt <= nchunks * (rank + 1):
                if len(localfrags) < nchunks:
                    localfrags.append(vineyard.ObjectID(chunk))
            cnt += 1

    print('chunks to migrate to local are: %s', localfrags)

    local_member_ids = []
    for chunk_id in localfrags:
        print('start migrate: %r' % chunk_id)
        local_id = client.migrate(chunk_id)
        local_member_ids.append(repr(local_id))
        print('finish migrate: %r -> %r' % (chunk_id, local_id))

    with open('/tmp/vineyard/vineyard.chunks', 'w', encoding='utf-8') as fp:
        fp.write('\n'.join(local_member_ids))


if __name__ == '__main__':
    if len(sys.argv) < 4:
        print('Usage: ./vineyard-migrate-to-local <replica> <rank> <global object>')
    replica = int(sys.argv[1])
    rank = int(sys.argv[2])
    object_id = vineyard.ObjectID(sys.argv[3])
    migrate_to_local(replica, rank, object_id)
