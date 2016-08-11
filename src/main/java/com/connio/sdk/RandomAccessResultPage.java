/**
 * Copyright (c) 2016 Connio Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Authors: juan@connio.com
 *
 */

package com.connio.sdk;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Parametrized DTO for resources read operation that provides random access pagination results.
 * @param <T>
 */
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
