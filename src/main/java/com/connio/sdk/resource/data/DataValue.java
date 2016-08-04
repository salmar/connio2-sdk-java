package com.connio.sdk.resource.data;

import com.connio.sdk.resource.property.Property;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import org.joda.time.DateTime;

import java.util.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DataValue {

    private final Object v;

    private final DateTime t;

    private final Map<String, Object> ann;


    @JsonCreator
    public DataValue(@JsonProperty("v") Object v,
                     @JsonProperty("t") DateTime t,
                     @JsonProperty("ann") Map<String, Object> ann) {
        this.v = v;
        this.t = t;
        this.ann = ann != null ? ann : Collections.emptyMap();
    }

    public Object getV() {
        return v;
    }

    public DateTime getT() {
        return t;
    }

    public Map<String, Object> getAnn() {
        return ann;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DataValue that = (DataValue) o;
        return Objects.equals(t, that.t) && Objects.equals(v, that.v) && Objects.equals(ann, that.ann);
    }

    @Override
    public int hashCode() {
        return Objects.hash(t, v, ann);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper("DataValue")
                .add("v", v)
                .add("t", t)
                .add("ann", ann)
                .toString();
    }
}