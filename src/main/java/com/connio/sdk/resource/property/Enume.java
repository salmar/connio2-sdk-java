package com.connio.sdk.resource.property;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class Enume {
    private final Set<String> text;

    private final Set<Number> numeric;

    @JsonCreator
    public Enume(@JsonProperty("text") Set<String> text,
                 @JsonProperty("numeric") Set<Number> numeric) {
        this.text = text;
        this.numeric = numeric;
    }

    public Set<String> getText() {
        return text;
    }

    public Set<Number> getNumeric() {
        return numeric;
    }

}
