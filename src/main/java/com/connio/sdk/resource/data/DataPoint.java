package com.connio.sdk.resource.data;

import com.connio.sdk.resource.property.Property;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableMap;
import org.joda.time.DateTime;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DataPoint {

    private final Object v;

    private final DateTime t;

    private final Long tt;

    private final ImmutableMap<String, Object> ann;


    private DataPoint(Object v, DateTime t, Long tt, ImmutableMap<String, Object> ann) {
        this.v = v;
        this.t = t;
        this.tt = tt;
        this.ann = ann;
    }

    public Object getV() {
        return v;
    }

    public DateTime getT() {
        return t;
    }

    public Long getTt() {
        return tt;
    }

    public Map<String, Object> getAnn() {
        return ann;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataPoint dataPoint = (DataPoint) o;
        return Objects.equals(getV(), dataPoint.getV()) &&
                Objects.equals(getT(), dataPoint.getT()) &&
                Objects.equals(getTt(), dataPoint.getTt()) &&
                Objects.equals(getAnn(), dataPoint.getAnn());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getV(), getT(), getTt(), getAnn());
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper("DataPoint")
                .add("v", v)
                .add("t", t)
                .add("tt", t)
                .add("ann", ann)
                .toString();
    }


    public static class Builder {

        private final Object v;

        private DateTime t;

        private Long tt;

        private Map<String, Object> ann;

        public Builder(Object v) {
            this.v = v;
            this.t = DateTime.now();
            this.ann = new HashMap<>();
        }

        public Object getV() {
            return v;
        }

        public DateTime getT() {
            return t;
        }

        public Builder setT(DateTime t) {
            this.t = t;
            this.tt = null;
            return this;
        }

        public Long getTt() {
            return tt;
        }

        public Builder setTt(Long tt) {
            this.tt = tt;
            this.t = null;
            return this;
        }

        public Map<String, Object> getAnn() {
            return ann;
        }

        public Builder setAnn(Map<String, Object> ann) {
            this.ann = ann;
            return this;
        }

        public Builder addAnnotation(String key, String value) {
            ann.put(key, value);
            return this;
        }

        public Builder removeAnnotations() {
            this.ann = null;
            return this;
        }

        public Builder setProperty(Property property) {
            this.ann.put("prop", property.getId());
            return this;
        }

        public DataPoint build() {
            return new DataPoint(v, t, tt, ann.isEmpty() ? null : ImmutableMap.copyOf(ann));
        }
    }
}