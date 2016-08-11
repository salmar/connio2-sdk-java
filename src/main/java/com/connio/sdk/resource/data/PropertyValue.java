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

import com.connio.sdk.resource.Location;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableMap;
import org.joda.time.DateTime;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class PropertyValue {

    private final Object mostRecent;

    private final DateTime time;

    private final Location location;

    private final Map<String, Object> ann;

    private final PropertyStats stats;

    public PropertyValue(@JsonProperty("mostRecent") final Object mostRecent,
                         @JsonProperty("time") final DateTime time,
                         @JsonProperty("ann") final Map<String, Object> ann,
                         @JsonProperty("stats") final PropertyStats stats,
                         @JsonProperty("location") final Location location) {

        this.mostRecent = mostRecent;
        this.time = time;
        this.ann = ann != null ? ann : ImmutableMap.of();
        this.stats = stats;
        this.location = location;
    }

    public Object getMostRecent() {
        return mostRecent;
    }

    public DateTime getTime() {
        return time;
    }

    public Map<String, Object> getAnn() {
        return ann;
    }

    public PropertyStats getStats() {
        return stats;
    }

    public Optional<Location> getLocation() {
        return Optional.ofNullable(location);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PropertyValue that = (PropertyValue) o;
        return Objects.equals(mostRecent, that.mostRecent) && Objects.equals(time, that.time) &&
                Objects.equals(location, that.location) && Objects.equals(ann, that.ann) &&
                Objects.equals(stats, that.stats);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mostRecent, time, location, ann, stats);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper("PropertyValue")
                .add("mostRecent", mostRecent)
                .add("time", time)
                .add("location", location)
                .add("ann", ann)
                .add("stats", stats)
                .toString();
    }

}
