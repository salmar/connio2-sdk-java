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

package com.connio.sdk.request;

import com.connio.sdk.Connio;
import com.connio.sdk.DefaultConnioApiClient;
import com.connio.sdk.http.Request;
import com.connio.sdk.http.Response;
import com.connio.sdk.request.alert.AlertDeleteRequest;
import com.connio.sdk.request.method.MethodUpdateRequest;
import com.connio.sdk.request.property.PropertyCreateRequest;
import com.connio.sdk.request.property.PropertyDeleteRequest;
import com.connio.sdk.request.property.PropertyUpdateRequest;
import com.connio.sdk.resource.deviceprofile.DeviceProfile;
import com.connio.sdk.resource.property.Property;
import com.google.common.collect.ImmutableSet;
import mockit.Expectations;
import mockit.Mocked;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;

public class PropertyRequestsTest {

    @Mocked
    private DefaultConnioApiClient connioApiClient;

    @Mocked
    private Response response;

    private final DeviceProfile dp = new DeviceProfile("_dpf_125632547856985412", "dp", "_acc_1256985412563258", null,
            "description", null, null, null, null, ImmutableSet.<String>of(), DateTime.now(), null);

    private final Property property = new Property("_prp_159357456987452136", "temperature", dp.getAccountId(), dp.getId(),
            null, false, Property.Type.Number, Property.Access.Public, Property.PublishPolicy.Always, false, "propertyQName",
            null, null, null, DateTime.now(), null);

    @Before
    public void setUp() throws Exception {
        Connio.init("key", "password");
    }

    @Test
    public void methodCreationShouldPerformExpectedRequest() throws Exception {
        new Expectations() {{
            final PropertyCreateRequest addRequest = new PropertyCreateRequest(dp, property.getName(), property.getType())
                    .setAccess(property.getAccess())
                    .setBoundaries(property.getBoundaries().orElse(null))
                    .setMeasurement(property.getMeasurement())
                    .setPublish(property.getPublish())
                    .setRetention(property.getRetention());

            final String path = "deviceprofiles/" + dp.getId() + "/properties";
            final Request request = Request.post(path, addRequest);
            response.isSuccess(); result = true;
            connioApiClient.request(request); times = 4;
            connioApiClient.requestAsync(request); result = CompletableFuture.completedFuture(response); times = 4;
        }};

        final PropertyCreateRequest request = Property.create(dp, property.getName(), property.getType())
                .setAccess(property.getAccess())
                .setBoundaries(property.getBoundaries().orElse(null))
                .setMeasurement(property.getMeasurement())
                .setPublish(property.getPublish())
                .setRetention(property.getRetention());

            request.execute();
            request.executeAsync();

            request.execute(connioApiClient);
            request.executeAsync(connioApiClient);

        final PropertyCreateRequest request2 = dp.addProperty(property.getName(), property.getType())
                .setAccess(property.getAccess())
                .setBoundaries(property.getBoundaries().orElse(null))
                .setMeasurement(property.getMeasurement())
                .setPublish(property.getPublish())
                .setRetention(property.getRetention());

        request2.execute();
        request2.executeAsync();

        request2.execute(connioApiClient);
        request2.executeAsync(connioApiClient);
    }

    @Test(expected=IllegalArgumentException.class)
    public void updateRequestShouldReturnIllegalArgumentExceptionWhenInvalidOwnerId() throws Exception {
        new MethodUpdateRequest("wrong_owner_Id", property.getId());
    }

    @Test
    public void methodUpdateRequestShouldPerformExpectedRequest() throws Exception {
        new Expectations() {{
            final PropertyUpdateRequest updateRequest = new PropertyUpdateRequest(dp, property.getId())
                    .setAccess(property.getAccess())
                    .setBoundaries(property.getBoundaries().orElse(null))
                    .setMeasurement(property.getMeasurement())
                    .setPublish(property.getPublish())
                    .setRetention(property.getRetention())
                    .setName(property.getName());

            final String path = "deviceprofiles/" + dp.getId() + "/properties/" + property.getId();
            final Request request = Request.put(path, updateRequest);
            response.isSuccess(); result = true;
            connioApiClient.request(request); times = 2;
            connioApiClient.requestAsync(request); result = CompletableFuture.completedFuture(response); times = 2;
        }};

        final PropertyUpdateRequest request = property.update()
                .setAccess(property.getAccess())
                .setBoundaries(property.getBoundaries().orElse(null))
                .setMeasurement(property.getMeasurement())
                .setPublish(property.getPublish())
                .setRetention(property.getRetention())
                .setName(property.getName());

        request.execute();
        request.executeAsync();

        request.execute(connioApiClient);
        request.executeAsync(connioApiClient);
    }

    @Test
    public void methodDeleteRequestShouldPerformExpectedRequest() throws Exception {
        new Expectations() {{
            final String path = "deviceprofiles/" + dp.getId() + "/properties/" + property.getId();
            final Request request = Request.delete(path);
            response.isSuccess(); result = true;
            connioApiClient.request(request); times = 2;
            connioApiClient.requestAsync(request); result = CompletableFuture.completedFuture(response); times = 2;
        }};

        final PropertyDeleteRequest request = property.delete();

        request.execute();
        request.executeAsync();

        request.execute(connioApiClient);
        request.executeAsync(connioApiClient);
    }

    @Test(expected=IllegalArgumentException.class)
    public void deleteRequestShouldReturnIllegalArgumentExceptionWhenInvalidOwnerId() {
        new AlertDeleteRequest("wrong_owner_Id", property.getId());
    }
}