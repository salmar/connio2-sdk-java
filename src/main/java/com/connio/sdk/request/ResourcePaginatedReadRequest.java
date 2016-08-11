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

package com.connio.sdk.request;

import com.connio.sdk.RandomAccessResultPage;
import com.connio.sdk.resource.Resource;

import java.util.HashMap;
import java.util.Map;

/**
 * Base abstract class for resource read with pagination requests.
 * @param <T>
 */
public abstract class ResourcePaginatedReadRequest<T extends Resource> extends ApiRequest<RandomAccessResultPage<T>> {

    protected Long pageSize;

    protected Long pageNumber;

    protected Long skip;

    public Long getPageSize() {
        return pageSize;
    }

    public ResourcePaginatedReadRequest setPageSize(Long pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public Long getPageNumber() {
        return pageNumber;
    }

    public ResourcePaginatedReadRequest setPageNumber(Long pageNumber) {
        this.pageNumber = pageNumber;
        return this;
    }

    public Long getSkip() {
        return skip;
    }

    public ResourcePaginatedReadRequest setSkip(Long skip) {
        this.skip = skip;
        return this;
    }

    public Map<String, String> getPaginationParameters() {
        final Map<String, String> result = new HashMap<>();

        if (pageSize != null) result.put("pageSize", String.valueOf(pageSize));
        if (pageNumber != null) result.put("pageNo", String.valueOf(pageNumber));
        if (skip != null) result.put("skip", String.valueOf(skip));

        return result;
    }
}