package com.connio.sdk.request;

import com.connio.sdk.Connio;
import com.connio.sdk.DefaultConnioApiClient;
import com.connio.sdk.http.Request;
import com.connio.sdk.http.Response;
import com.connio.sdk.request.device.DeviceCreateRequest;
import com.connio.sdk.request.device.DeviceDeleteRequest;
import com.connio.sdk.request.device.DeviceUpdateRequest;
import com.connio.sdk.resource.device.Device;
import com.connio.sdk.resource.deviceprofile.DeviceProfile;
import com.google.common.collect.ImmutableSet;
import mockit.Expectations;
import mockit.Mocked;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;

public class DeviceRequestsTest {

    @Mocked
    private DefaultConnioApiClient connioApiClient;

    @Mocked
    private Response response;

    private final DeviceProfile dp = new DeviceProfile("_dpf_125632547856985412", "dp", "_acc_1256985412563258", null,
            "description", null, null, null, null, ImmutableSet.<String>of(), DateTime.now(), null);

    private final Device device = new Device("_dev_125632547856956325", "device", dp.getAccountId(), dp.getId(),
            "_app_12569854785632587", Device.Status.Enabled, 0, null, null, null, true, true, null, null, DateTime.now(), null);

    @Before
    public void setUp() throws Exception {
        Connio.init("key", "password");
    }

    @Test
    public void addRequestShouldPerformExpectedRequest() throws Exception {
        new Expectations() {{
            final DeviceCreateRequest addRequest = new DeviceCreateRequest(dp)
                    .setAnnotateWithLoc(device.isAnnotateWithLoc())
                    .setAnnotateWithMeta(device.isAnnotateWithMeta())
                    .setCustomIds(device.getCustomIds())
                    .setLocation(device.getLocation().orElse(null))
                    .setName(device.getName())
                    .setNotes(device.getNotes().orElse(""))
                    .setPeriod(1)
                    .setStatus(Device.Status.Enabled)
                    .setTags(device.getTags())
                    .setTimezone(device.getTimezone().orElse(""));

            final Request request = Request.post("devices", addRequest);
            response.isSuccess(); result = true;
            connioApiClient.request(request); times = 2;
            connioApiClient.requestAsync(request); times = 2;
        }};

        final DeviceCreateRequest request = Device.create(dp)
                .setAnnotateWithLoc(device.isAnnotateWithLoc())
                .setAnnotateWithMeta(device.isAnnotateWithMeta())
                .setCustomIds(device.getCustomIds())
                .setLocation(device.getLocation().orElse(null))
                .setName(device.getName())
                .setNotes(device.getNotes().orElse(""))
                .setPeriod(1)
                .setStatus(Device.Status.Enabled)
                .setTags(device.getTags())
                .setTimezone(device.getTimezone().orElse(""));

            request.execute();
            request.executeAsync();

            request.execute(connioApiClient);
            request.executeAsync(connioApiClient);
    }

    @Test
    public void updateRequestShouldPerformExpectedRequest() throws Exception {
        new Expectations() {{
            final DeviceUpdateRequest updateRequest = new DeviceUpdateRequest(device)
                    .setAnnotateWithLoc(device.isAnnotateWithLoc())
                    .setAnnotateWithMeta(device.isAnnotateWithMeta())
                    .setCustomIds(device.getCustomIds())
                    .setLocation(device.getLocation().orElse(null))
                    .setName(device.getName())
                    .setNotes(device.getNotes().orElse(""))
                    .setPeriod(1)
                    .setStatus(Device.Status.Enabled)
                    .setTags(device.getTags())
                    .setTimezone(device.getTimezone().orElse(""));

            final String path = "devices/" + device.getId();
            final Request request = Request.put(path, updateRequest);
            response.isSuccess(); result = true;
            connioApiClient.request(request); times = 2;
            connioApiClient.requestAsync(request); result = CompletableFuture.completedFuture(response); times = 2;
        }};

        final DeviceUpdateRequest request = device.update()
                .setAnnotateWithLoc(device.isAnnotateWithLoc())
                .setAnnotateWithMeta(device.isAnnotateWithMeta())
                .setCustomIds(device.getCustomIds())
                .setLocation(device.getLocation().orElse(null))
                .setName(device.getName())
                .setNotes(device.getNotes().orElse(""))
                .setPeriod(1)
                .setStatus(Device.Status.Enabled)
                .setTags(device.getTags())
                .setTimezone(device.getTimezone().orElse(""));

        request.execute();
        request.executeAsync();

        request.execute(connioApiClient);
        request.executeAsync(connioApiClient);
    }

    @Test
    public void deleteRequestShouldPerformExpectedRequest() throws Exception {
        new Expectations() {{
            final String path = "devices/" + device.getId();
            final Request request = Request.delete(path);
            response.isSuccess(); result = true;
            connioApiClient.request(request); times = 2;
            connioApiClient.requestAsync(request); result = CompletableFuture.completedFuture(response); times = 2;
        }};


        final DeviceDeleteRequest request = device.delete();

        request.execute();
        request.executeAsync();

        request.execute(connioApiClient);
        request.executeAsync(connioApiClient);
    }
}