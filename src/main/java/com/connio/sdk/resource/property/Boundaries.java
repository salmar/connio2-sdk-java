package com.connio.sdk.resource.property;

import com.connio.sdk.resource.Context;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class Boundaries {

    private final Context context;

    private final Enume enums;

    private final Size size;

    private final Range range;

    private final GeoFence geofence;

    @JsonCreator
    public Boundaries(@JsonProperty("context") Context context,
                      @JsonProperty("enums") Enume enumeration,
                      @JsonProperty("size") Size size,
                      @JsonProperty("range") Range range,
                      @JsonProperty("geofence") GeoFence geofence) {
        this.context = context;
        this.enums = enumeration;
        this.size = size;
        this.range = range;
        this.geofence = geofence;
    }

    public Context getContext() {
        return context;
    }

    public Enume getEnums() {
        return enums;
    }

    public Size getSize() {
        return size;
    }

    public Range getRange() {
        return range;
    }

    public GeoFence getGeofence() {
        return geofence;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        Boundaries other = (Boundaries) obj;
        return Objects.equal(this.getContext(), other.getContext());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getContext());
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper("Boundaries")
                .add("context", getContext())
                .toString();
    }

}