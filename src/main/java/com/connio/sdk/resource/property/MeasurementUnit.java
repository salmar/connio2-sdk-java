package com.connio.sdk.resource.property;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class MeasurementUnit {

    private final String label;

    private final String symbol;

    @JsonCreator
    public MeasurementUnit(@JsonProperty("label") String label,
                           @JsonProperty("symbol") String symbol) {
        this.label = label;
        this.symbol = symbol;
    }

    public String getLabel() {
        return label;
    }

    public String getSymbol() {
        return symbol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MeasurementUnit that = (MeasurementUnit) o;
        return Objects.equals(label, that.label) && Objects.equals(symbol, that.symbol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(label, symbol);
    }
}