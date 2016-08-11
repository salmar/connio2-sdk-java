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

package com.connio.sdk.request.deviceprofile;

import com.connio.sdk.RandomAccessResultPage;
import com.connio.sdk.http.Request;
import com.connio.sdk.http.Response;
import com.connio.sdk.request.ResourcePaginatedReadRequest;
import com.connio.sdk.resource.deviceprofile.DeviceProfile;
import org.apache.commons.lang3.StringUtils;

import javax.ws.rs.core.GenericType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Device profile read request used to read device profiles and specify pagination.
 */
public class DeviceProfileReadRequest extends ResourcePaginatedReadRequest<DeviceProfile> {

    private String base;


    private List<String> tags;


    public String getBase() {
        return base;
    }

    public DeviceProfileReadRequest setBase(String base) {
        this.base = base;
        return this;
    }

    public List<String> getTags() {
        return tags;
    }

    public DeviceProfileReadRequest setTags(List<String> tags) {
        this.tags = tags;
        return this;
    }

    @Override
    protected Request request() {
        final Map<String, String> filter = new HashMap<>();

        if (StringUtils.isNotBlank(base)) filter.put("base", base);
        if (tags != null && tags.size() > 0) filter.put("tags", StringUtils.join(tags, ","));

        filter.putAll(getPaginationParameters());

        return Request.get("deviceprofiles", filter);
    }

    @Override
    protected RandomAccessResultPage<DeviceProfile> parseEntity(Response response) {
        return response.readEntity(new GenericType<RandomAccessResultPage<DeviceProfile>>(){});
    }
}
