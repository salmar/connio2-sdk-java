package com.connio.sdk.resource.property;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class Range {
    private final Number min;

    private final Number max;

    @JsonCreator
    public Range(@JsonProperty("min") Number min,
                 @JsonProperty("max") Number max) {
        this.min = min;
        this.max = max;
    }

    public Number getMin() {
        return min;
    }

    public Number getMax() {
        return max;
    }

}
