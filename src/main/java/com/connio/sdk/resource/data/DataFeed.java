package com.connio.sdk.resource.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.common.base.MoreObjects;

import java.util.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DataFeed {

    private final Map<String, Object> ann;

    private final List<DataPoint> dps;

    public DataFeed(Map<String, Object> ann, List<DataPoint> dps) {
        this.ann = ann;
        this.dps = dps;
    }

    public DataFeed(List<DataPoint> dps) {
        this.ann = null;
        this.dps = dps;
    }

    public DataFeed(DataPoint... dps) {
        this.ann = null;
        this.dps = Arrays.asList(dps);
    }

    public Map<String, Object> getAnn() {
        return ann;
    }

    public List<DataPoint> getDps() {
        return dps;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DataFeed that = (DataFeed) o;
        return Objects.equals(ann, that.ann) && Objects.equals(dps, that.dps);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ann, dps);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper("DataFeed")
                .add("ann", ann)
                .add("dps", dps)
                .toString();
    }
}