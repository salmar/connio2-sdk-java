package com.connio.sdk;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class RandomAccessResultPage<T> {

    private final Long total;

    private final Long itemCount;

    private final Long numOfPages;

    private final Long pageNo;

    private final Long skip;

    private final List<T> results;


    @JsonCreator
    public RandomAccessResultPage(@JsonProperty("total") Long total,
                                  @JsonProperty("itemCount") Long itemCount,
                                  @JsonProperty("numOfPages") Long numOfPages,
                                  @JsonProperty("pageNo") Long pageNo,
                                  @JsonProperty("skip") Long skip,
                                  @JsonProperty("results") List<T> results) {

        this.total = total;
        this.itemCount = itemCount;
        this.numOfPages = numOfPages;
        this.pageNo = pageNo;
        this.skip = skip;
        this.results = results;
    }

    public Long getTotal() {
        return total;
    }

    public Long getItemCount() {
        return itemCount;
    }

    public Long getNumOfPages() {
        return numOfPages;
    }

    public Long getPageNo() {
        return pageNo;
    }

    public Long getSkip() {
        return skip;
    }

    public List<T> getResults() {
        return results;
    }
}
