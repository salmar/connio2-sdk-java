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
import com.connio.sdk.request.ApiRequest;
import com.connio.sdk.resource.alert.Alert;
import com.connio.sdk.resource.device.Device;

/**
 * Alert incident remove account. It is used to acknowledge alert notification and stop with notification lifecycle.
 */
public class AlertRemoveIncidentRequest extends ApiRequest<Void> {

    private final Device device;

    private final Alert alert;

    public AlertRemoveIncidentRequest(Device device, Alert alert) {
        this.device = device;
        this.alert = alert;
    }

    public Device getDevice() {
        return device;
    }

    public Alert getAlert() {
        return alert;
    }

    @Override
    protected Request request() {
        final String path = "devices/" + device.getId() + "/alerts/" + alert.getId() + "/incident";
        return Request.delete(path);
    }

    @Override
    protected Void parseEntity(Response response) {
        return null;
    }
}
