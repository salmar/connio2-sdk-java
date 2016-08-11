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

import com.connio.sdk.http.Request;
import com.connio.sdk.http.Response;
import com.connio.sdk.request.ResourceReadRequest;
import com.connio.sdk.resource.alert.Alert;
import com.connio.sdk.resource.device.Device;
import com.connio.sdk.resource.deviceprofile.DeviceProfile;

import javax.ws.rs.core.GenericType;
import java.util.List;

/**
 * Method read request used to read methods.
 */
public class MethodReadRequest extends ResourceReadRequest<Alert> {

    private final String ownerId;

    public MethodReadRequest(DeviceProfile deviceProfile) {
        this.ownerId = deviceProfile.getId();
    }

    public MethodReadRequest(Device device) {
        this.ownerId = device.getId();
    }

    @Override
    protected Request request() {
        final String ownerPath = ownerId.startsWith("_dpf_") ? "deviceprofiles" : "devices";
        final String path = ownerPath + "/" + ownerId +"/methods";
        return Request.get(path);
    }

    @Override
    protected List<Alert> parseEntity(Response response) {
        return response.readEntity(new GenericType<List<Alert>>(){});
    }
}