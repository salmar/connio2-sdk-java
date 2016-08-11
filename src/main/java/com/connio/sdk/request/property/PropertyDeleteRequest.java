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

import com.connio.sdk.request.ResourceDeleteRequest;
import com.connio.sdk.http.Request;
import com.connio.sdk.resource.deviceprofile.DeviceProfile;

/**
 * Property delete request used to delete properties.
 */
public class PropertyDeleteRequest extends ResourceDeleteRequest {

    private final String ownerId;

    private final String propertyId;

    public PropertyDeleteRequest(String deviceProfileId, String propertyId) {
        this.ownerId = deviceProfileId;
        this.propertyId = propertyId;

        if (deviceProfileId == null || !deviceProfileId.startsWith("_dpf_"))
            throw new IllegalArgumentException("Invalid device profile id");
    }

    public PropertyDeleteRequest(DeviceProfile deviceProfile, String propertyId) {
        this.ownerId = deviceProfile.getId();
        this.propertyId = propertyId;
    }

    @Override
    protected Request request() {
        final String path = "deviceprofiles/" + ownerId + "/properties/" + propertyId;
        return Request.delete(path);
    }
}
