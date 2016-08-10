package com.connio.sdk.resource.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

import java.util.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DataFeed {

    private final ImmutableMap<String, Object> ann;

    private final ImmutableList<DataPoint> dps;

    public DataFeed(ImmutableMap<String, Object> ann, ImmutableList<DataPoint> dps) {
        this.ann = ann;
        this.dps = dps;
    }

    public DataFeed(ImmutableList<DataPoint> dps) {
        this.ann = null;
        this.dps = dps;
    }

    public DataFeed(DataPoint... dps) {
        this.ann = null;
        this.dps = ImmutableList.copyOf(Arrays.asList(dps));
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
        DataFeed dataFeed = (DataFeed) o;
        return Objects.equals(getAnn(), dataFeed.getAnn()) &&
                Objects.equals(getDps(), dataFeed.getDps());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAnn(), getDps());
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper("DataFeed")
                .add("ann", ann)
                .add("dps", dps)
                .toString();
    }
}