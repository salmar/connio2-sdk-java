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

package com.connio.sdk.request.data;

import com.connio.sdk.http.Request;
import com.connio.sdk.http.Response;
import com.connio.sdk.request.ApiRequest;
import com.connio.sdk.resource.data.DataFeed;
import com.connio.sdk.resource.device.Device;
import com.connio.sdk.resource.property.Property;

/**
 * Data feed write request used to write data to a device property.
 */
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
