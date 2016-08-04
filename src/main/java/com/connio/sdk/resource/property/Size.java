package com.connio.sdk.resource.property;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class Size {
    private final Long value;

    @JsonCreator
    public Size(@JsonProperty("value") Long value) {
        this.value = value;
    }

    public Long getValue() {
        return value;
    }

}
