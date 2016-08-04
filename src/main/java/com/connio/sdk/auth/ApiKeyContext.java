package com.connio.sdk.auth;

import com.fasterxml.jackson.annotation.*;
import com.google.common.collect.ImmutableSet;
import org.apache.commons.lang3.text.WordUtils;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class ApiKeyContext {

    public enum Type {
        Account,
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

    private final ImmutableSet<String> ids;

    @JsonCreator
    public ApiKeyContext(@JsonProperty("type") Type type, @JsonProperty("id") ImmutableSet<String> ids) {
        this.type = type;
        this.ids = ids;
    }

    public Type getType() {
        return type;
    }

    public ImmutableSet<String> getIds() {
        return ids;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ApiKeyContext that = (ApiKeyContext) o;
        return Objects.equals(type, that.type) && Objects.equals(ids, that.ids);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, ids);
    }
}
