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
