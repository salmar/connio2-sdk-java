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

import com.fasterxml.jackson.annotation.*;
import org.apache.commons.lang3.text.WordUtils;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class Condition {

    public enum When {
        Changed,
        Always,
        ChangedByXPercent;

        @JsonValue
        public String value() {
            return name().substring(0, 1).toLowerCase() + name().substring(1);
        }

        @JsonCreator
        public static When fromValue(@JsonProperty("name") final String name) {
            return When.valueOf(WordUtils.capitalize(name));
        }
    }

    private final When when;

    private final Integer value;

    @JsonCreator
    public Condition(@JsonProperty("when") When when,
                     @JsonProperty("value") Integer value) {
        this.value = value;
        this.when = when;
    }

    public When getWhen() {
        return when;
    }

    public Integer getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Condition that = (Condition) o;
        return Objects.equals(when, that.when) && Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(when, value);
    }
}
