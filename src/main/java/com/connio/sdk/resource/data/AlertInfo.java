package com.connio.sdk.resource.data;

import com.connio.sdk.resource.alert.Alert;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;

import java.util.Objects;
import java.util.Optional;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AlertInfo {

    private final InfoHeader descriptors;

    private final Alert meta;

    private final AlertValue value;

    public AlertInfo(@JsonProperty("descriptors") final InfoHeader descriptors,
                     @JsonProperty("meta") final Alert meta,
                     @JsonProperty("value") final AlertValue value) {
        this.descriptors = descriptors;
        this.meta = meta;
        this.value = value;
    }

    public InfoHeader getDescriptors() {
        return descriptors;
    }

    public Optional<Alert> getMeta() {
        return Optional.ofNullable(meta);
    }

    public Optional<AlertValue> getValue() {
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
        AlertInfo other = (AlertInfo) obj;
        return Objects.equals(this.descriptors, other.descriptors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(descriptors);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper("AlertInfo")
                .add("descriptors", descriptors)
                .add("meta", meta)
                .add("value", value)
                .toString();
    }
}
