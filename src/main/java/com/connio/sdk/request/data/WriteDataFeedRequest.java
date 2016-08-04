package com.connio.sdk.request.data;

import com.connio.sdk.http.Request;
import com.connio.sdk.http.Response;
import com.connio.sdk.request.ApiRequest;
import com.connio.sdk.resource.data.DataFeed;
import com.connio.sdk.resource.device.Device;
import com.connio.sdk.resource.property.Property;

public class WriteDataFeedRequest extends ApiRequest<Void> {

    private final String deviceId;

    private Property property;

    private final DataFeed dataFeed;

    public WriteDataFeedRequest(Device device, Property property, DataFeed dataFeed) {
        this.deviceId = device.getId();
        this.property = property;
        this.dataFeed = dataFeed;
    }

    public WriteDataFeedRequest(Device device, DataFeed dataFeed) {
        this.deviceId = device.getId();
        this.dataFeed = dataFeed;
    }

    public Property getProperty() {
        return property;
    }

    public WriteDataFeedRequest setProperty(Property property) {
        this.property = property;
        return this;
    }

    public DataFeed getDataFeed() {
        return dataFeed;
    }

    @Override
    protected Request request() {
        final String basePath = "data/devices/" + deviceId;
        final String path = property != null ? basePath + "/properties/" + property.getId() : basePath + "/properties";
        return Request.post(path, dataFeed);
    }

    @Override
    protected Void parseEntity(Response response) {
        return null;
    }
}
