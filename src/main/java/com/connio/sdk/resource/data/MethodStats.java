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
