package com.connio.sdk;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;

import java.util.List;

/**
 * Parametrized DTO for resources read operation that provides sequential access pagination results.
 * @param <T>
 */
public class SequentialAccessResultPage<T> {

    private final Long total;

    private final Long itemCount;

    private final List<T> results;

    private final String next;


    @JsonCreator
    public SequentialAccessResultPage(@JsonProperty("total") Long total,
                                      @JsonProperty("itemCount") Long itemCount,
                                      @JsonProperty("results") List<T> results,
                                      @JsonProperty("next") String next) {

        this.total = total;
        this.itemCount = itemCount;
        this.results = results;
        this.next = next;
    }

    public Long getTotal() {
        return total;
    }

    public Long getItemCount() {
        return itemCount;
    }

    public List<T> getResults() {
        return results;
    }

    public String getNext() {
        return next;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper("SequentialAccessResultPage")
                .add("total", total)
                .add("itemCount", itemCount)
                .add("results", results)
                .add("next", next)
                .toString();
    }
}
