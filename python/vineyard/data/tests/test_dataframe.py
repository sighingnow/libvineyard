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

import pandas as pd
import pytest
import numpy as np

import vineyard
from vineyard.core import default_builder_context, default_resolver_context
from vineyard.data import register_builtin_types

register_builtin_types(default_builder_context, default_resolver_context)


def test_pandas_dataframe(vineyard_client):
    df = pd.DataFrame({'a': [1, 2, 3, 4], 'b': [5, 6, 7, 8]})
    object_id = vineyard_client.put(df)
    pd.testing.assert_frame_equal(df, vineyard_client.get(object_id))


def test_pandas_dataframe_int_columns(vineyard_client):
    df = pd.DataFrame({1: [1, 2, 3, 4], 2: [5, 6, 7, 8]})
    object_id = vineyard_client.put(df)
    pd.testing.assert_frame_equal(df, vineyard_client.get(object_id))


def test_pandas_dataframe_mixed_columns(vineyard_client):
    df = pd.DataFrame({'a': [1, 2, 3, 4], 'b': [5, 6, 7, 8], 1: [9, 10, 11, 12], 2: [13, 14, 15, 16]})
    object_id = vineyard_client.put(df)
    pd.testing.assert_frame_equal(df, vineyard_client.get(object_id))


def test_dataframe_reindex(vineyard_client):
    df = pd.DataFrame(np.random.rand(10, 5), columns=['c1', 'c2', 'c3', 'c4', 'c5'])
    expected = df.reindex(index=np.arange(10, 1, -1))
    object_id = vineyard_client.put(expected)
    pd.testing.assert_frame_equal(expected, vineyard_client.get(object_id))


def test_dataframe_set_index(vineyard_client):
    df1 = pd.DataFrame([[1, 3, 3], [4, 2, 6], [7, 8, 9]], index=['a1', 'a2', 'a3'], columns=['x', 'y', 'z'])
    expected = df1.set_index('y', drop=True)
    object_id = vineyard_client.put(expected)
    pd.testing.assert_frame_equal(expected, vineyard_client.get(object_id))


def test_sparse_array(vineyard_client):
    arr = np.random.randn(10)
    arr[2:5] = np.nan
    arr[7:8] = np.nan
    sparr = pd.arrays.SparseArray(arr)
    object_id = vineyard_client.put(sparr)
    pd.testing.assert_extension_array_equal(sparr, vineyard_client.get(object_id))


def test_dataframe_with_sparse_array(vineyard_client):
    df = pd.DataFrame(np.random.randn(100, 4), columns=['x', 'y', 'z', 'a'])
    df.iloc[:98] = np.nan
    sdf = df.astype(pd.SparseDtype("float", np.nan))
    object_id = vineyard_client.put(sdf)
    pd.testing.assert_frame_equal(df, vineyard_client.get(object_id))


def test_dataframe_with_sparse_array_int_columns(vineyard_client):
    df = pd.DataFrame(np.random.randn(100, 4), columns=[1, 2, 3, 4])
    df.iloc[:98] = np.nan
    sdf = df.astype(pd.SparseDtype("float", np.nan))
    object_id = vineyard_client.put(sdf)
    pd.testing.assert_frame_equal(df, vineyard_client.get(object_id))


def test_dataframe_with_sparse_array_mixed_columns(vineyard_client):
    df = pd.DataFrame(np.random.randn(100, 4), columns=['x', 'y', 'z', 0])
    df.iloc[:98] = np.nan
    sdf = df.astype(pd.SparseDtype("float", np.nan))
    object_id = vineyard_client.put(sdf)
    pd.testing.assert_frame_equal(df, vineyard_client.get(object_id))
