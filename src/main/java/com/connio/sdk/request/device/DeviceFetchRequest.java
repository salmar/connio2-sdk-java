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

package com.connio.sdk.request.device;

import com.connio.sdk.request.ResourceFetchRequest;
import com.connio.sdk.http.Request;
import com.connio.sdk.http.Response;
import com.connio.sdk.resource.device.Device;
import org.apache.commons.lang3.StringUtils;

/**
 * Device fetch request used to fetch a device.
 */
public class DeviceFetchRequest extends ResourceFetchRequest<Device> {

    private final String deviceId;

    public DeviceFetchRequest(String deviceId) {
        this.deviceId = deviceId;
    }

    public DeviceFetchRequest() {
        this.deviceId = "";
    }

    @Override
    protected Request request() {
        final String deviceIdentificator = (StringUtils.isNotBlank(deviceId) ? deviceId : "_this_");
        final String path = "devices/" + deviceIdentificator;
        return Request.get(path);
    }

    @Override
    protected Device parseResourceEntity(Response response) {
        return response.readEntity(Device.class);
    }
}
