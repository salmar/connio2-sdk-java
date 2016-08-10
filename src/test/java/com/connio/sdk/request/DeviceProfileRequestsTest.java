package com.connio.sdk.request;

import com.connio.sdk.Connio;
import com.connio.sdk.ConnioApiClient;
import com.connio.sdk.ConnioApiClientImpl;
import com.connio.sdk.http.JerseyClient;
import com.connio.sdk.http.Request;
import com.connio.sdk.http.Response;
import com.connio.sdk.request.device.DeviceAddRequest;
import com.connio.sdk.request.device.DeviceDeleteRequest;
import com.connio.sdk.request.device.DeviceUpdateRequest;
import com.connio.sdk.request.deviceprofile.DeviceProfileAddRequest;
import com.connio.sdk.request.deviceprofile.DeviceProfileDeleteRequest;
import com.connio.sdk.request.deviceprofile.DeviceProfileUpdateRequest;
import com.connio.sdk.resource.device.Device;
import com.connio.sdk.resource.deviceprofile.DeviceProfile;
import com.google.common.collect.ImmutableSet;
import mockit.Expectations;
import mockit.Mocked;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;

public class DeviceProfileRequestsTest {

    @Mocked
    private JerseyClient httpClient;

    @Mocked
    private Response response;

    private final DeviceProfile dp = new DeviceProfile("_dpf_125632547856985412", "dp", "_acc_1256985412563258", null,
            "description", null, null, null, null, ImmutableSet.<String>of(), DateTime.now(), null);

    @Before
    public void setUp() throws Exception {
        Connio.init("key", "password");
    }

    @Test
    public void addRequestShouldPerformExpectedRequest() throws Exception {
        new Expectations() {{
            final DeviceProfileAddRequest addRequest = new DeviceProfileAddRequest(dp.getName())
                    .setDescription(dp.getDescription().orElse(""))
                    .setDeviceClass(dp.getDeviceClass().orElse(""))
                    .setImageUri(dp.getImageUri().orElse(""))
                    .setProductName(dp.getProductName().orElse(""))
                    .setTags(dp.getTags())
                    .setVendorName(dp.getVendorName().orElse(""));

            final Request request = Request.post("deviceprofiles", addRequest);
            response.isSuccess(); result = true;
            httpClient.request(request); times = 2;
            httpClient.requestAsync(request); times = 2;
        }};

        final DeviceProfileAddRequest request = DeviceProfile.create(dp.getName())
                .setDescription(dp.getDescription().orElse(""))
                .setDeviceClass(dp.getDeviceClass().orElse(""))
                .setImageUri(dp.getImageUri().orElse(""))
                .setProductName(dp.getProductName().orElse(""))
                .setTags(dp.getTags())
                .setVendorName(dp.getVendorName().orElse(""));

            request.execute();
            request.executeAsync();

            final ConnioApiClient apiClient = new ConnioApiClientImpl("key", "secret");
            request.execute(apiClient);
            request.executeAsync(apiClient);
    }

    @Test
    public void updateRequestShouldPerformExpectedRequest() throws Exception {
        new Expectations() {{
            final DeviceProfileUpdateRequest updateRequest = new DeviceProfileUpdateRequest(dp)
                    .setDescription(dp.getDescription().orElse(""))
                    .setDeviceClass(dp.getDeviceClass().orElse(""))
                    .setImageUri(dp.getImageUri().orElse(""))
                    .setProductName(dp.getProductName().orElse(""))
                    .setTags(dp.getTags())
                    .setVendorName(dp.getVendorName().orElse(""))
                    .setName(dp.getName());

            final String path = "deviceprofiles/" + dp.getId();
            final Request request = Request.put(path, updateRequest);
            response.isSuccess(); result = true;
            httpClient.request(request); times = 2;
            httpClient.requestAsync(request); result = CompletableFuture.supplyAsync(() -> response); times = 2;
        }};

        final DeviceProfileUpdateRequest request = dp.update()
                .setDescription(dp.getDescription().orElse(""))
                .setDeviceClass(dp.getDeviceClass().orElse(""))
                .setImageUri(dp.getImageUri().orElse(""))
                .setProductName(dp.getProductName().orElse(""))
                .setTags(dp.getTags())
                .setVendorName(dp.getVendorName().orElse(""))
                .setName(dp.getName());

        request.execute();
        request.executeAsync();

        final ConnioApiClient apiClient = new ConnioApiClientImpl("key", "secret");
        request.execute(apiClient);
        request.executeAsync(apiClient);
    }

    @Test
    public void deleteRequestShouldPerformExpectedRequest() throws Exception {
        new Expectations() {{
            final String path = "deviceprofiles/" + dp.getId();
            final Request request = Request.delete(path);
            response.isSuccess(); result = true;
            httpClient.request(request); times = 2;
            httpClient.requestAsync(request); result = CompletableFuture.supplyAsync(() -> response); times = 2;
        }};

        final DeviceProfileDeleteRequest request = dp.delete();

        request.execute();
        request.executeAsync();

        final ConnioApiClient apiClient = new ConnioApiClientImpl("key", "secret");
        request.execute(apiClient);
        request.executeAsync(apiClient);
    }
}