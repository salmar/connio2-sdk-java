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