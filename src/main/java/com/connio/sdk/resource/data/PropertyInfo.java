package com.connio.sdk.resource.data;

import com.connio.sdk.resource.property.Property;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import java.util.Optional;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PropertyInfo {

    private final InfoHeader descriptors;

    private final Property meta;

    private final PropertyValue value;

    public PropertyInfo(@JsonProperty("descriptors") final InfoHeader descriptors,
                        @JsonProperty("meta") final Property meta,
                        @JsonProperty("value") final PropertyValue value) {

        this.descriptors = descriptors;
        this.meta = meta;
        this.value = value;
    }

    public InfoHeader getDescriptors() {
        return descriptors;
    }

    public Optional<Property> getMeta() {
        return Optional.ofNullable(meta);
    }

    public Optional<PropertyValue> getValue() {
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
        PropertyInfo other = (PropertyInfo) obj;
        return Objects.equal(this.descriptors, other.descriptors);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(descriptors);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper("PropertyInfo")
                .add("descriptors", descriptors)
                .add("meta", meta)
                .add("value", value)
                .toString();
    }

}
