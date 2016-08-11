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
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;

import java.util.Objects;
import java.util.Optional;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class MethodStats {

    private final Long count;

    private final Number min;

    private final Number max;

    private final Number sum;

    private final Long failures;


    @JsonCreator
    public MethodStats(@JsonProperty("count") Long count,
                       @JsonProperty("min") Number min,
                       @JsonProperty("max") Number max,
                       @JsonProperty("sum") Number sum,
                       @JsonProperty("failures") Long failures) {

        this.count = count;
        this.min = min;
        this.max = max;
        this.sum = sum;
        this.failures = failures != null ? failures : 0L;
    }

    public Long getCount() {
        return count;
    }

    public Optional<Number> getMin() {
        return Optional.ofNullable(min);
    }

    public Optional<Number> getMax() {
        return Optional.ofNullable(max);
    }

    public Optional<Number> getSum() {
        return Optional.ofNullable(sum);
    }

    public Long getFailures() {
        return failures;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MethodStats that = (MethodStats) o;
        return Objects.equals(count, that.count) && Objects.equals(min, that.min) && Objects.equals(max, that.max) &&
                Objects.equals(sum, that.sum) && Objects.equals(failures, that.failures);
    }

    @Override
    public int hashCode() {
        return Objects.hash(count, min, max, sum, failures);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper("MethodStats")
                .add("count", count)
                .add("min", min)
                .add("max", max)
                .add("sum", sum)
                .add("failures", failures)
                .toString();
    }
}
