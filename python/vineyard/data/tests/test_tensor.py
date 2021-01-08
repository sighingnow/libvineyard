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

import numpy as np
import pandas as pd
import pytest

import vineyard
from vineyard.core import default_builder_context, default_resolver_context
from vineyard.data import register_builtin_types

register_builtin_types(default_builder_context, default_resolver_context)


def test_numpy_ndarray(vineyard_client):
    arr = np.random.rand(4, 5, 6)
    object_id = vineyard_client.put(arr)
    np.testing.assert_allclose(arr, vineyard_client.get(object_id))


def test_empty_ndarray(vineyard_client):
    arr = np.ones(())
    object_id = vineyard_client.put(arr)
    np.testing.assert_allclose(arr, vineyard_client.get(object_id))

    arr = np.ones((0, 1))
    object_id = vineyard_client.put(arr)
    np.testing.assert_allclose(arr, vineyard_client.get(object_id))

    arr = np.ones((0, 1, 2))
    object_id = vineyard_client.put(arr)
    np.testing.assert_allclose(arr, vineyard_client.get(object_id))

    arr = np.ones((0, 1, 2, 3))
    object_id = vineyard_client.put(arr)
    np.testing.assert_allclose(arr, vineyard_client.get(object_id))

    arr = np.zeros((), dtype='int')
    object_id = vineyard_client.put(arr)
    np.testing.assert_allclose(arr, vineyard_client.get(object_id))

    arr = np.zeros((0, 1), dtype='int')
    object_id = vineyard_client.put(arr)
    np.testing.assert_allclose(arr, vineyard_client.get(object_id))

    arr = np.zeros((0, 1, 2), dtype='int')
    object_id = vineyard_client.put(arr)
    np.testing.assert_allclose(arr, vineyard_client.get(object_id))

    arr = np.zeros((0, 1, 2, 3), dtype='int')
    object_id = vineyard_client.put(arr)
    np.testing.assert_allclose(arr, vineyard_client.get(object_id))
