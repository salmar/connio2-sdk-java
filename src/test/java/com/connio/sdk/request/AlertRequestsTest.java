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
import com.connio.sdk.ConnioApiClient;
import com.connio.sdk.DefaultConnioApiClient;
import com.connio.sdk.http.Request;
import com.connio.sdk.http.Response;
import com.connio.sdk.request.alert.AlertCreateRequest;
import com.connio.sdk.request.alert.AlertDeleteRequest;
import com.connio.sdk.request.alert.AlertUpdateRequest;
import com.connio.sdk.resource.alert.*;
import com.connio.sdk.resource.device.Device;
import com.connio.sdk.resource.deviceprofile.DeviceProfile;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import mockit.Expectations;
import mockit.Mocked;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;

public class AlertRequestsTest {

    @Mocked
    private DefaultConnioApiClient connioApiClient;

    @Mocked
    private Response response;

    private final DeviceProfile dp = new DeviceProfile("_dpf_125632547856985412", "dp", "_acc_1256985412563258", null,
            "description", null, null, null, null, ImmutableSet.<String>of(), DateTime.now(), null);

    private final Device dev = new Device("_dev_125632547856956325", "device", dp.getAccountId(), dp.getId(),
            "_app_12569854785632587", Device.Status.Enabled, 0, null, null, null, true, true, null, null, DateTime.now(), null);

    private final Notification notification = new LogNotification("log", "message", LogNotification.LogLevel.Error);

    private final AlertHandler handler = new AlertHandler("1", notification.getName(), null, null);

    private final AlertCheck check = new AlertCheck(AlertCheck.Severity.Critical, "metric > 10", ImmutableList.of(handler));


    private final Alert alertDeviceProfile = new Alert("_ale_125632547856958745", "alertName", dp.getAccountId(),
            "_prp_125478569325874125", dp.getId(), "description", ImmutableSet.<String>of(),
            Alert.Status.On, "value", ImmutableList.of(check), null, ImmutableList.of(notification), DateTime.now(), null);

    private final Alert alertDevice = new Alert("_ale_125632547856958745", "alertName", dp.getAccountId(),
            "_prp_125478569325874125", dev.getId(), "description", ImmutableSet.<String>of(),
            Alert.Status.On, "value", ImmutableList.of(check), null, ImmutableList.of(notification), DateTime.now(), null);

    @Before
    public void setUp() throws Exception {
        Connio.init("key", "password");
    }

    @Test
    public void deviceProfileAlertCreationShouldPerformExpectedRequest() throws Exception {
        new Expectations() {{
            final AlertCreateRequest addRequest = new AlertCreateRequest(dp, alertDeviceProfile.getName(), alertDeviceProfile.getTriggerPropId(),
                    alertDeviceProfile.getMetric(), alertDeviceProfile.getChecks(), alertDeviceProfile.getNotifications())
                    .setDescription(alertDeviceProfile.getDescription().orElse(""))
                    .setRecover(alertDeviceProfile.getRecover().orElse(null))
                    .setStatus(alertDeviceProfile.getStatus())
                    .setTags(alertDeviceProfile.getTags());


            final String path = "deviceprofiles/" + dp.getId() + "/alerts";
            final Request request = Request.post(path, addRequest);
            response.isSuccess(); result = true;
            connioApiClient.request(request); times = 4;
            connioApiClient.requestAsync(request); result = CompletableFuture.completedFuture(response); times = 4;
        }};

        final ConnioApiClient apiClient = new DefaultConnioApiClient("key", "secret");

        final AlertCreateRequest request = Alert.create(dp, alertDeviceProfile.getName(), alertDeviceProfile.getTriggerPropId(),
                alertDeviceProfile.getMetric(), alertDeviceProfile.getChecks(), alertDeviceProfile.getNotifications())
                .setDescription(alertDeviceProfile.getDescription().orElse(""))
                .setRecover(alertDeviceProfile.getRecover().orElse(null))
                .setStatus(alertDeviceProfile.getStatus())
                .setTags(alertDeviceProfile.getTags());

            request.execute();
            request.executeAsync();

            request.execute(apiClient);
            request.executeAsync(apiClient);

        final AlertCreateRequest request2 = dp.addAlert(alertDeviceProfile.getName(), alertDeviceProfile.getTriggerPropId(),
                alertDeviceProfile.getMetric(), alertDeviceProfile.getChecks(), alertDeviceProfile.getNotifications())
                .setDescription(alertDeviceProfile.getDescription().orElse(""))
                .setRecover(alertDeviceProfile.getRecover().orElse(null))
                .setStatus(alertDeviceProfile.getStatus())
                .setTags(alertDeviceProfile.getTags());

        request2.execute();
        request2.executeAsync();

        request2.execute(apiClient);
        request2.executeAsync(apiClient);
    }


    @Test
    public void deviceAlertCreationShouldPerformExpectedRequest() throws Exception {
        new Expectations() {{
            final AlertCreateRequest addRequest = new AlertCreateRequest(dev, alertDevice.getName(), alertDevice.getTriggerPropId(),
                    alertDevice.getMetric(), alertDevice.getChecks(), alertDevice.getNotifications())
                    .setDescription(alertDevice.getDescription().orElse(""))
                    .setRecover(alertDevice.getRecover().orElse(null))
                    .setStatus(alertDevice.getStatus())
                    .setTags(alertDevice.getTags());


            final String path = "devices/" + dev.getId() + "/alerts";
            final Request request = Request.post(path, addRequest);
            response.isSuccess(); result = true;
            connioApiClient.request(request); times = 4;
            connioApiClient.requestAsync(request); result = CompletableFuture.completedFuture(response); times = 4;
        }};

        final AlertCreateRequest request = Alert.create(dev, alertDevice.getName(), alertDevice.getTriggerPropId(), alertDevice.getMetric(),
                alertDevice.getChecks(), alertDevice.getNotifications())
                .setDescription(alertDevice.getDescription().orElse(""))
                .setRecover(alertDevice.getRecover().orElse(null))
                .setStatus(alertDevice.getStatus())
                .setTags(alertDevice.getTags());

        request.execute();
        request.executeAsync();

        request.execute(connioApiClient);
        request.executeAsync(connioApiClient);

        final AlertCreateRequest request2 = dev.addAlert(alertDevice.getName(), alertDevice.getTriggerPropId(), alertDevice.getMetric(),
                alertDeviceProfile.getChecks(), alertDevice.getNotifications())
                .setDescription(alertDevice.getDescription().orElse(""))
                .setRecover(alertDevice.getRecover().orElse(null))
                .setStatus(alertDevice.getStatus())
                .setTags(alertDevice.getTags());

        request2.execute();
        request2.executeAsync();

        request2.execute(connioApiClient);
        request2.executeAsync(connioApiClient);
    }

    @Test(expected=IllegalArgumentException.class)
    public void updateRequestShouldReturnIllegalArgumentExceptionWhenInvalidOwnerId() throws Exception {
        new AlertUpdateRequest("wrong_owner_Id", alertDeviceProfile.getId());
    }

    @Test
    public void deviceProfileAlertUpdateRequestShouldPerformExpectedRequest() throws Exception {
        new Expectations() {{
            final AlertUpdateRequest updateRequest = new AlertUpdateRequest(dp, alertDeviceProfile.getId())
                    .setChecks(alertDeviceProfile.getChecks())
                    .setDescription(alertDeviceProfile.getDescription().orElse(""))
                    .setMetric(alertDeviceProfile.getMetric())
                    .setNotifications(alertDeviceProfile.getNotifications())
                    .setRecover(alertDeviceProfile.getRecover().orElse(null))
                    .setStatus(alertDeviceProfile.getStatus())
                    .setTags(alertDeviceProfile.getTags())
                    .setTriggerPropId(alertDeviceProfile.getTriggerPropId());

            final String path = "deviceprofiles/" + dp.getId() + "/alerts/" + alertDeviceProfile.getId();
            final Request request = Request.put(path, updateRequest);
            response.isSuccess(); result = true;
            connioApiClient.request(request); times = 2;
            connioApiClient.requestAsync(request); result = CompletableFuture.completedFuture(response); times = 2;
        }};

        final AlertUpdateRequest request = alertDeviceProfile.update()
                .setChecks(alertDeviceProfile.getChecks())
                .setDescription(alertDeviceProfile.getDescription().orElse(""))
                .setMetric(alertDeviceProfile.getMetric())
                .setNotifications(alertDeviceProfile.getNotifications())
                .setRecover(alertDeviceProfile.getRecover().orElse(null))
                .setStatus(alertDeviceProfile.getStatus())
                .setTags(alertDeviceProfile.getTags())
                .setTriggerPropId(alertDeviceProfile.getTriggerPropId());

        request.execute();
        request.executeAsync();

        request.execute(connioApiClient);
        request.executeAsync(connioApiClient);
    }


    @Test
    public void deviceAlertUpdateRequestShouldPerformExpectedRequest() throws Exception {
        new Expectations() {{
            final AlertUpdateRequest updateRequest = new AlertUpdateRequest(dev, alertDevice.getId())
                    .setChecks(alertDevice.getChecks())
                    .setDescription(alertDevice.getDescription().orElse(""))
                    .setMetric(alertDevice.getMetric())
                    .setNotifications(alertDevice.getNotifications())
                    .setRecover(alertDevice.getRecover().orElse(null))
                    .setStatus(alertDevice.getStatus())
                    .setTags(alertDevice.getTags())
                    .setTriggerPropId(alertDevice.getTriggerPropId());

            final String path = "devices/" + dev.getId() + "/alerts/" + alertDevice.getId();
            final Request request = Request.put(path, updateRequest);
            response.isSuccess(); result = true;
            connioApiClient.request(request); times = 2;
            connioApiClient.requestAsync(request); result = CompletableFuture.completedFuture(response); times = 2;
        }};

        final AlertUpdateRequest request = alertDevice.update()
                .setChecks(alertDevice.getChecks())
                .setDescription(alertDevice.getDescription().orElse(""))
                .setMetric(alertDevice.getMetric())
                .setNotifications(alertDevice.getNotifications())
                .setRecover(alertDevice.getRecover().orElse(null))
                .setStatus(alertDevice.getStatus())
                .setTags(alertDevice.getTags())
                .setTriggerPropId(alertDevice.getTriggerPropId());

        request.execute();
        request.executeAsync();

        request.execute(connioApiClient);
        request.executeAsync(connioApiClient);
    }

    @Test
    public void deviceProfileAlertDeleteRequestShouldPerformExpectedRequest() throws Exception {
        new Expectations() {{
            final String path = "deviceprofiles/" + dp.getId() + "/alerts/" + alertDeviceProfile.getId();
            final Request request = Request.delete(path);
            response.isSuccess(); result = true;
            connioApiClient.request(request); times = 2;
            connioApiClient.requestAsync(request); result = CompletableFuture.completedFuture(response); times = 2;
        }};

        final AlertDeleteRequest request = alertDeviceProfile.delete();

        request.execute();
        request.executeAsync();

        request.execute(connioApiClient);
        request.executeAsync(connioApiClient);
    }

    @Test
    public void deviceAlertDeleteRequestShouldPerformExpectedRequest() throws Exception {
        new Expectations() {{
            final String path = "devices/" + dev.getId() + "/alerts/" + alertDevice.getId();
            final Request request = Request.delete(path);
            response.isSuccess(); result = true;
            connioApiClient.request(request); times = 2;
            connioApiClient.requestAsync(request); result = CompletableFuture.completedFuture(response); times = 2;
        }};

        final AlertDeleteRequest request = alertDevice.delete();

        request.execute();
        request.executeAsync();

        request.execute(connioApiClient);
        request.executeAsync(connioApiClient);
    }

    @Test(expected=IllegalArgumentException.class)
    public void deleteRequestShouldReturnIllegalArgumentExceptionWhenInvalidOwnerId() {
        new AlertDeleteRequest("wrong_owner_Id", alertDeviceProfile.getId());
    }
}