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
import com.connio.sdk.response.MethodResult;
import com.connio.sdk.resource.device.Device;
import com.connio.sdk.resource.method.Method;

/**
 * Read public "getter" methods, that is, public methods that don't accept parameters and are connected to a property.
 */
public class ReadPublicMethodRequest extends ApiRequest<Object> {

    private final String deviceId;

    private final String methodId;

    public ReadPublicMethodRequest(Device device, Method method) {
        this.deviceId = device.getId();
        this.methodId = method.getId();
    }

    @Override
    protected Request request() {
        final String path = "data/devices/" + deviceId + "/methods/" + methodId;
        return Request.get(path);
    }

    @Override
    protected Object parseEntity(Response response) {
        return response.readEntity(MethodResult.class).getResult();
    }
}