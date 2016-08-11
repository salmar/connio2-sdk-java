package com.connio.sdk.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;

import java.util.Objects;

/**
 * Connio generic entity response when returning method results.
 */
public class MethodResult {

    private final Object result;

    @JsonCreator
    public MethodResult(@JsonProperty("result") Object result) {
        this.result = result;
    }

    public Object getResult() {
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MethodResult that = (MethodResult) o;
        return Objects.equals(result, that.result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(result);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper("MethodResult")
                .add("result", result)
                .toString();
    }
}
