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

import com.connio.sdk.resource.alert.Alert;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;

import java.util.Objects;
import java.util.Optional;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AlertInfo {

    private final InfoHeader descriptors;

    private final Alert meta;

    private final AlertValue value;

    public AlertInfo(@JsonProperty("descriptors") final InfoHeader descriptors,
                     @JsonProperty("meta") final Alert meta,
                     @JsonProperty("value") final AlertValue value) {
        this.descriptors = descriptors;
        this.meta = meta;
        this.value = value;
    }

    public InfoHeader getDescriptors() {
        return descriptors;
    }

    public Optional<Alert> getMeta() {
        return Optional.ofNullable(meta);
    }

    public Optional<AlertValue> getValue() {
        return Optional.ofNullable(value);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        AlertInfo other = (AlertInfo) obj;
        return Objects.equals(this.descriptors, other.descriptors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(descriptors);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper("AlertInfo")
                .add("descriptors", descriptors)
                .add("meta", meta)
                .add("value", value)
                .toString();
    }
}
