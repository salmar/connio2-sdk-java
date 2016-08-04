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
public class PropertyStats {

    private final Long count;

    private final Number min;

    private final Number max;

    private final Number sum;


    @JsonCreator
    public PropertyStats(@JsonProperty("count") Long count,
                         @JsonProperty("min") Number min,
                         @JsonProperty("max") Number max,
                         @JsonProperty("sum") Number sum) {

        this.count = count;
        this.min = min;
        this.max = max;
        this.sum = sum;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PropertyStats that = (PropertyStats) o;
        return Objects.equals(count, that.count) && Objects.equals(min, that.min) && Objects.equals(max, that.max) &&
                Objects.equals(sum, that.sum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(count, min, max, sum);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper("PropertyStats")
                .add("count", count)
                .add("min", min)
                .add("max", max)
                .add("sum", sum)
                .toString();
    }
}
