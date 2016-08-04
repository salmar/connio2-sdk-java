package com.connio.sdk.resource.property;

import com.connio.sdk.resource.Context;
import com.fasterxml.jackson.annotation.*;
import org.apache.commons.lang3.text.WordUtils;

import java.util.Objects;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class Retention {

    public enum Type {
        Historical,
        Mostrecent;

        @JsonValue
        public String value() {
            return name().toLowerCase();
        }

        @JsonCreator
        public static Type fromValue(@JsonProperty("name") final String name) {
            return Type.valueOf(WordUtils.capitalize(name.toLowerCase()));
        }
    }

    private final Context context;

    private final Type type;

    private final String lifetime;

    private final Long capacity;

    private final Condition condition;

    @JsonCreator
    public Retention(@JsonProperty("context") Context context,
                     @JsonProperty("type") Type type,
                     @JsonProperty("lifetime") String lifetime,
                     @JsonProperty("capacity") Long capacity,
                     @JsonProperty("condition") Condition condition) {
        this.context = context;
        this.type = type == null ? Type.Historical : type;
        this.lifetime = lifetime;
        this.capacity = capacity;
        this.condition = condition;
    }

    public Context getContext() {
        return context;
    }

    public Type getType() {
        return type;
    }

    public String getLifetime() {
        return lifetime;
    }

    public Long getCapacity() {
        return capacity;
    }

    public Condition getCondition() {
        return condition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Retention that = (Retention) o;
        return Objects.equals(context, that.context) && Objects.equals(type, that.type)
                && Objects.equals(lifetime, that.lifetime) & Objects.equals(capacity, that.capacity)
                && Objects.equals(condition, that.condition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(context, type, lifetime, capacity, condition);
    }
}
