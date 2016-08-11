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

package com.connio.sdk.request.property;

import com.connio.sdk.http.Request;
import com.connio.sdk.http.Response;
import com.connio.sdk.request.ResourceReadRequest;
import com.connio.sdk.resource.alert.Alert;
import com.connio.sdk.resource.device.Device;
import com.connio.sdk.resource.deviceprofile.DeviceProfile;
import com.connio.sdk.resource.property.Property;
import org.apache.commons.lang3.StringUtils;

import javax.ws.rs.core.GenericType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Property read request used to read properties.
 */
public class PropertyReadRequest extends ResourceReadRequest<Property> {

    private final String ownerId;

    private String type;

    public PropertyReadRequest(DeviceProfile deviceProfile) {
        this.ownerId = deviceProfile.getId();
    }

    public String getType() {
        return type;
    }

    public PropertyReadRequest setType(String type) {
        this.type = type;
        return this;
    }

    @Override
    protected Request request() {
        final String path = "deviceprofiles/" + ownerId + "/properties";

        final Map<String, String> filter = new HashMap<>();
        if (StringUtils.isNotBlank(type)) filter.put("type", type);

        return Request.get(path, filter);
    }

    @Override
    protected List<Property> parseEntity(Response response) {
        return response.readEntity(new GenericType<List<Property>>(){});
    }
}