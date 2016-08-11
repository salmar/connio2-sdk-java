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

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import org.joda.time.DateTime;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DataValue {

    private final Object v;

    private final DateTime t;

    private final Map<String, Object> ann;


    @JsonCreator
    public DataValue(@JsonProperty("v") Object v,
                     @JsonProperty("t") DateTime t,
                     @JsonProperty("ann") Map<String, Object> ann) {
        this.v = v;
        this.t = t;
        this.ann = ann != null ? ann : Collections.emptyMap();
    }

    public Object getV() {
        return v;
    }

    public DateTime getT() {
        return t;
    }

    public Map<String, Object> getAnn() {
        return ann;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DataValue that = (DataValue) o;
        return Objects.equals(t, that.t) && Objects.equals(v, that.v) && Objects.equals(ann, that.ann);
    }

    @Override
    public int hashCode() {
        return Objects.hash(t, v, ann);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper("DataValue")
                .add("v", v)
                .add("t", t)
                .add("ann", ann)
                .toString();
    }
}