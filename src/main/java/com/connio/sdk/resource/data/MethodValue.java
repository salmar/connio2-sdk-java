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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import org.joda.time.DateTime;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class MethodValue {

    private final Object mostRecent;

    private final DateTime lastCalledAt;

    private final Boolean quarantined;

    private final MethodStats stats;

    public MethodValue(@JsonProperty("mostRecent") final Object mostRecent,
                       @JsonProperty("lastCalledAt") final DateTime lastCalledAt,
                       @JsonProperty("quarantined") final Boolean quarantined,
                       @JsonProperty("stats") final MethodStats stats) {

        this.mostRecent = mostRecent;
        this.lastCalledAt = lastCalledAt;
        this.quarantined = quarantined == null ? false : quarantined;
        this.stats = stats;
    }

    public Object getMostRecent() {
        return mostRecent;
    }

    public DateTime getLastCalledAt() {
        return lastCalledAt;
    }

    public Boolean isQuarantined() {
        return quarantined;
    }

    public MethodStats getStats() {
        return stats;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MethodValue that = (MethodValue) o;
        return Objects.equals(mostRecent, that.mostRecent) && Objects.equals(lastCalledAt, that.lastCalledAt) &&
                Objects.equals(quarantined, that.quarantined) && Objects.equals(stats, that.stats);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mostRecent, lastCalledAt, quarantined, stats);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper("MethodStats")
                .add("mostRecent", mostRecent)
                .add("lastCalledAt", lastCalledAt)
                .add("quarantined", quarantined)
                .add("stats", stats)
                .toString();
    }
}
