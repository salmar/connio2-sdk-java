package com.connio.sdk.resource;

import com.fasterxml.jackson.annotation.*;
import com.google.common.base.MoreObjects;
import org.apache.commons.lang3.text.WordUtils;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class Context {

    public enum Type {
        Default,
        App,
        Device;

        @JsonValue
        public String value() {
            return name().toLowerCase();
        }

        @JsonCreator
        public static Type fromValue(@JsonProperty("name") final String name) {
            return Type.valueOf(WordUtils.capitalize(name.toLowerCase()));
        }
    }

    private final Type type;

    private final String id;    // entity id corresponding to context type


    @JsonCreator
    public Context(@JsonProperty("type") Type type, @JsonProperty("id") String id) {
        this.type = type;
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Context that = (Context) o;
        return Objects.equals(type, that.type) && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, id);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper("Context")
                .add("type", getType().value())
                .add("id", getId())
                .toString();
    }
}
