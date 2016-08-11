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

package com.connio.sdk.resource.property;

import com.connio.sdk.resource.Context;
import com.fasterxml.jackson.annotation.*;
import org.apache.commons.lang3.text.WordUtils;

import java.util.Objects;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class Retention {

    public enum Type {
        Historical,
        Mostrecent;

        @JsonValue
        public String value() {
            return name().toLowerCase();
        }

        @JsonCreator
        public static Type fromValue(@JsonProperty("name") final String name) {
            return Type.valueOf(WordUtils.capitalize(name.toLowerCase()));
        }
    }

    private final Context context;

    private final Type type;

    private final String lifetime;

    private final Long capacity;

    private final Condition condition;

    @JsonCreator
    public Retention(@JsonProperty("context") Context context,
                     @JsonProperty("type") Type type,
                     @JsonProperty("lifetime") String lifetime,
                     @JsonProperty("capacity") Long capacity,
                     @JsonProperty("condition") Condition condition) {
        this.context = context;
        this.type = type == null ? Type.Historical : type;
        this.lifetime = lifetime;
        this.capacity = capacity;
        this.condition = condition;
    }

    public Context getContext() {
        return context;
    }

    public Type getType() {
        return type;
    }

    public String getLifetime() {
        return lifetime;
    }

    public Long getCapacity() {
        return capacity;
    }

    public Condition getCondition() {
        return condition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Retention that = (Retention) o;
        return Objects.equals(context, that.context) && Objects.equals(type, that.type)
                && Objects.equals(lifetime, that.lifetime) & Objects.equals(capacity, that.capacity)
                && Objects.equals(condition, that.condition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(context, type, lifetime, capacity, condition);
    }
}
