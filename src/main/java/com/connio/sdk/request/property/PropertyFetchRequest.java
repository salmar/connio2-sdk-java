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

import com.connio.sdk.request.ResourceFetchRequest;
import com.connio.sdk.http.Request;
import com.connio.sdk.http.Response;
import com.connio.sdk.resource.deviceprofile.DeviceProfile;
import com.connio.sdk.resource.property.Property;

/**
 * Property fetch request used to fetch a property.
 */
public class PropertyFetchRequest extends ResourceFetchRequest<Property> {

    private final String deviceProfileId;

    private final String propertyId;

    public PropertyFetchRequest(DeviceProfile deviceProfile, String propertyId) {
        this.deviceProfileId = deviceProfile.getId();
        this.propertyId = propertyId;
    }

    @Override
    protected Request request() {
        final String path = "deviceprofiles/" + deviceProfileId + "/properties/" + propertyId;
        return Request.get(path);
    }

    @Override
    protected Property parseResourceEntity(Response response) {
        return response.readEntity(Property.class);
    }
}