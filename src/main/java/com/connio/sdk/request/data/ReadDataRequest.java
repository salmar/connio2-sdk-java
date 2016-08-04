package com.connio.sdk.request.data;

import com.connio.sdk.SequentialAccessResultPage;
import com.connio.sdk.http.Request;
import com.connio.sdk.http.Response;
import com.connio.sdk.request.ApiRequest;
import com.connio.sdk.resource.data.DataValue;
import com.connio.sdk.resource.device.Device;
import com.connio.sdk.resource.property.Property;
import org.apache.commons.lang3.StringUtils;

import javax.ws.rs.core.GenericType;
import java.util.HashMap;
import java.util.Map;

public class ReadDataRequest extends ApiRequest<SequentialAccessResultPage<DataValue>> {

    private final String deviceId;

    private final String propertyId;

    private String query;

    private String sort;

    private String bookmark;

    private Long limit;

    private Long skip;

    public ReadDataRequest(Device device, Property property) {
        this.deviceId = device.getId();
        this.propertyId = property.getId();
    }

    public String getQuery() {
        return query;
    }

    public ReadDataRequest setQuery(String query) {
        this.query = query;
        return this;
    }

    public String getSort() {
        return sort;
    }

    public ReadDataRequest setSort(String sort) {
        this.sort = sort;
        return this;
    }

    public String getBookmark() {
        return bookmark;
    }

    public ReadDataRequest setBookmark(String bookmark) {
        this.bookmark = bookmark;
        return this;
    }

    public Long getLimit() {
        return limit;
    }

    public ReadDataRequest setLimit(Long limit) {
        this.limit = limit;
        return this;
    }

    public Long getSkip() {
        return skip;
    }

    public ReadDataRequest setSkip(Long skip) {
        this.skip = skip;
        return this;
    }

    @Override
    protected Request request() {
        final String path = "data/devices/" + deviceId + "/properties/" + propertyId;
        final Map<String, String> filter = new HashMap<>();

        if (StringUtils.isNotBlank(sort)) filter.put("sort", sort);
        if (StringUtils.isNotBlank(bookmark)) filter.put("bookmark", bookmark);
        if (limit != null) filter.put("limit", String.valueOf(limit));
        if (query != null) filter.put("q", query);
        if (skip != null) filter.put("skip", String.valueOf(skip));

        return Request.get(path, filter);
    }

    @Override
    protected SequentialAccessResultPage<DataValue> parseEntity(Response response) {
        return response.readEntity(new GenericType<SequentialAccessResultPage<DataValue>>(){});
    }
}
