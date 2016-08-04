package com.connio.sdk.resource.data;

import com.connio.sdk.resource.method.Method;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;

import java.util.Objects;
import java.util.Optional;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MethodInfo {

    private final InfoHeader descriptors;

    private final Method meta;

    private final MethodValue value;

    public MethodInfo(@JsonProperty("descriptors") final InfoHeader descriptors,
                      @JsonProperty("meta") final Method meta,
                      @JsonProperty("value") final MethodValue value) {

        this.descriptors = descriptors;
        this.meta = meta;
        this.value = value;
    }

    public InfoHeader getDescriptors() {
        return descriptors;
    }

    public Optional<Method> getMeta() {
        return Optional.ofNullable(meta);
    }

    public Optional<MethodValue> getValue() {
        return Optional.ofNullable(value);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        MethodInfo other = (MethodInfo) obj;
        return Objects.equals(this.descriptors, other.descriptors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(descriptors);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper("MethodInfo")
                .add("descriptors", descriptors)
                .add("meta", meta)
                .add("value", value)
                .toString();
    }

}
