/**
 * Copyright (c) 2016 Connio Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Authors: juan@connio.com
 *
 */

package com.connio.sdk.resource.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

import java.util.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DataFeed {

    private final ImmutableMap<String, Object> ann;

    private final ImmutableList<DataPoint> dps;

    public DataFeed(ImmutableMap<String, Object> ann, ImmutableList<DataPoint> dps) {
        this.ann = ann;
        this.dps = dps;
    }

    public DataFeed(ImmutableList<DataPoint> dps) {
        this.ann = null;
        this.dps = dps;
    }

    public DataFeed(DataPoint... dps) {
        this.ann = null;
        this.dps = ImmutableList.copyOf(Arrays.asList(dps));
    }

    public Map<String, Object> getAnn() {
        return ann;
    }

    public List<DataPoint> getDps() {
        return dps;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataFeed dataFeed = (DataFeed) o;
        return Objects.equals(getAnn(), dataFeed.getAnn()) &&
                Objects.equals(getDps(), dataFeed.getDps());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAnn(), getDps());
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper("DataFeed")
                .add("ann", ann)
                .add("dps", dps)
                .toString();
    }
}