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

package com.connio.sdk.request.method;

import com.connio.sdk.request.ResourceDeleteRequest;
import com.connio.sdk.http.Request;
import com.connio.sdk.resource.deviceprofile.DeviceProfile;

/**
 * Method delete request used to delete methods.
 */
public class MethodDeleteRequest extends ResourceDeleteRequest {

    private final String ownerId;

    private final String methodId;

    public MethodDeleteRequest(String deviceProfileId, String methodId) {
        this.ownerId = deviceProfileId;
        this.methodId = methodId;

        if (deviceProfileId == null || !deviceProfileId.startsWith("_dpf_"))
            throw new IllegalArgumentException("Invalid device profile");
    }

    public MethodDeleteRequest(DeviceProfile deviceProfile, String methodId) {
        this.ownerId = deviceProfile.getId();
        this.methodId = methodId;
    }

    @Override
    protected Request request() {
        final String path = "deviceprofiles/" + ownerId + "/methods/" + methodId;
        return Request.delete(path);
    }
}
