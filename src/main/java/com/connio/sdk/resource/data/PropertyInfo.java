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

import com.connio.sdk.resource.property.Property;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import java.util.Optional;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PropertyInfo {

    private final InfoHeader descriptors;

    private final Property meta;

    private final PropertyValue value;

    public PropertyInfo(@JsonProperty("descriptors") final InfoHeader descriptors,
                        @JsonProperty("meta") final Property meta,
                        @JsonProperty("value") final PropertyValue value) {

        this.descriptors = descriptors;
        this.meta = meta;
        this.value = value;
    }

    public InfoHeader getDescriptors() {
        return descriptors;
    }

    public Optional<Property> getMeta() {
        return Optional.ofNullable(meta);
    }

    public Optional<PropertyValue> getValue() {
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
        PropertyInfo other = (PropertyInfo) obj;
        return Objects.equal(this.descriptors, other.descriptors);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(descriptors);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper("PropertyInfo")
                .add("descriptors", descriptors)
                .add("meta", meta)
                .add("value", value)
                .toString();
    }

}
