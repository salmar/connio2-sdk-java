package com.connio.sdk.resource.property;

import com.fasterxml.jackson.annotation.*;
import org.apache.commons.lang3.text.WordUtils;

import java.util.Objects;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class Measurement {

    public enum Type {
        Custom,
        Time,
        Acceleration,
        Currency,
        Electricity,
        FitnessEnergy,
        Weight,
        LengthDistance,
        RelativeHumidity,
        Light,
        Pressure,
        Speed,
        SoundsLevel,
        Temperature,
        Percentage;

        @JsonValue
        public String value() {
            return WordUtils.uncapitalize(name());
        }

        @JsonCreator
        public static Type fromValue(@JsonProperty("name") final String name) {
            return Type.valueOf(WordUtils.capitalize(name));
        }
    }

    private final Type type;

    private final MeasurementUnit unit;

    @JsonCreator
    public Measurement(@JsonProperty("type") Type type,
                       @JsonProperty("unit") MeasurementUnit unit) {
        this.type = type;
        this.unit = unit == null ? new MeasurementUnit("unit", "-") : unit;
    }

    public Type getType() {
        return type;
    }

    public MeasurementUnit getUnit() {
        return unit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Measurement that = (Measurement) o;
        return Objects.equals(type, that.type) && Objects.equals(unit, that.unit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, unit);
    }
}

