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

package com.connio.sdk.resource;

import com.connio.sdk.resource.property.Geo;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import java.util.Optional;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class Location {

    private final String zone;

    private final Geo geo;

    @JsonCreator
    public Location(@JsonProperty("zone") String zone,
                    @JsonProperty("geo") Geo geo) {
        this.zone = zone;
        this.geo = geo;
    }

    public String getZone() {
        return zone;
    }

    public Optional<Geo> getGeo() {
        return Optional.ofNullable(geo);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        Location other = (Location) obj;
        return Objects.equal(this.getZone(), other.getZone()) &&
                Objects.equal(this.getGeo(), other.getGeo());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getZone(), getGeo());
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper("Location")
                .add("zone", getZone().toString())
                .add("geo", getGeo().toString())
                .toString();
    }

}
