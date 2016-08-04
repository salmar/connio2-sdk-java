package com.connio.sdk.resource.property;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

public class Geo {

    private final Double lat;

    private final Double lon;

    private final Double alt;

    @JsonCreator
    public Geo(@JsonProperty("lat") Double lat,
               @JsonProperty("lon") Double lon,
               @JsonProperty("alt") Double alt) {
        this.lat = lat;
        this.lon = lon;
        this.alt = alt;
    }

    public Double getLat() {
        return lat;
    }

    public Double getLon() {
        return lon;
    }

    public Double getAlt() {
        return alt;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        Geo other = (Geo) obj;
        return Objects.equal(this.getLat(), other.getLat()) &&
                Objects.equal(this.getLon(), other.getLon()) &&
                Objects.equal(this.getAlt(), other.getAlt());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getLat(), getLon(), getAlt());
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper("Location")
                .add("lat", getLat().toString())
                .add("lon", getLon().toString())
                .add("alt", getAlt() == null ? "-" : getAlt().toString())
                .toString();
    }

}
