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

package com.connio.sdk.request.alert;

import com.connio.sdk.http.Request;
import com.connio.sdk.http.Response;
import com.connio.sdk.request.ResourceFetchRequest;
import com.connio.sdk.resource.alert.Alert;
import com.connio.sdk.resource.device.Device;
import com.connio.sdk.resource.deviceprofile.DeviceProfile;

/**
 * Alert fetch request used to fetch an alert.
 */
public class AlertFetchRequest extends ResourceFetchRequest<Alert> {

    private final String ownerId;

    private final String alertId;

    public AlertFetchRequest(DeviceProfile deviceProfile, String alertId) {
        this.ownerId = deviceProfile.getId();
        this.alertId = alertId;
    }

    public AlertFetchRequest(Device device, String alertId) {
        this.ownerId = device.getId();
        this.alertId = alertId;
    }

    @Override
    protected Request request() {
        final String ownerPath = (ownerId.startsWith("_dpf_")) ? "deviceprofiles" : "devices";
        final String path = ownerPath + "/" + ownerId + "/alerts/" + alertId;

        return Request.get(path);
    }

    @Override
    protected Alert parseResourceEntity(Response response) {
        return response.readEntity(Alert.class);
    }
}