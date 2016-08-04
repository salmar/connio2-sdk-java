package com.connio.sdk.resource.property;

import com.fasterxml.jackson.annotation.*;
import org.apache.commons.lang3.text.WordUtils;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class GeoFence {

    public enum Position {
        In,
        Out;

        @JsonValue
        public String value() {
            return name().toLowerCase();
        }

        @JsonCreator
        public static Position fromValue(@JsonProperty("name") final String name) {
            return Position.valueOf(WordUtils.capitalize(name.toLowerCase()));
        }
    }

    private final Double lat;

    private final Double lon;

    private final Double radius;

    private final Position pos;

    @JsonCreator
    public GeoFence(@JsonProperty("lat") Double lat,
                    @JsonProperty("lon") Double lon,
                    @JsonProperty("radius") Double radius,
                    @JsonProperty("pos") Position pos) {
        this.lat = lat;
        this.lon = lon;
        this.radius = radius;
        this.pos = pos;
    }

    public Double getLat() {
        return lat;
    }

    public Double getLon() {
        return lon;
    }

    public Double getRadius() {
        return radius;
    }

    public Position getPos() {
        return pos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GeoFence that = (GeoFence) o;
        return Objects.equals(lat, that.lat) &&
                Objects.equals(lon, that.lon) &&
                Objects.equals(radius, that.radius) &&
                Objects.equals(pos, that.pos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lat, lon, radius, pos);
    }


}
