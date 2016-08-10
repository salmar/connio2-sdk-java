package com.connio.sdk.request;

import com.connio.sdk.Connio;
import com.connio.sdk.ConnioApiClient;
import com.connio.sdk.ConnioApiClientImpl;
import com.connio.sdk.http.JerseyClient;
import com.connio.sdk.http.Request;
import com.connio.sdk.http.Response;
import com.connio.sdk.request.alert.AlertDeleteRequest;
import com.connio.sdk.request.alert.AlertUpdateRequest;
import com.connio.sdk.request.data.*;
import com.connio.sdk.resource.data.DataFeed;
import com.connio.sdk.resource.data.DataPoint;
import com.connio.sdk.resource.data.MethodResult;
import com.connio.sdk.resource.device.Device;
import com.connio.sdk.resource.deviceprofile.DeviceProfile;
import com.connio.sdk.resource.method.Method;
import com.connio.sdk.resource.method.MethodImpl;
import com.connio.sdk.resource.property.Property;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import mockit.Expectations;
import mockit.Mocked;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class DataRequestsTest {

    @Mocked
    private JerseyClient httpClient;

    @Mocked
    private Response response;

    private final DeviceProfile dp = new DeviceProfile("_dpf_125632547856985412", "dp", "_acc_1256985412563258", null,
            "description", null, null, null, null, ImmutableSet.<String>of(), DateTime.now(), null);

    private final Device device = new Device("_dev_125632547856956325", "device", dp.getAccountId(), dp.getId(),
            "_app_12569854785632587", Device.Status.Enabled, 0, null, null, null, true, true, null, null, DateTime.now(), null);

    private final Property property = new Property("_prp_159357456987452136", "temperature", dp.getAccountId(), dp.getId(),
            null, false, Property.Type.Number, Property.Access.Public, Property.PublishPolicy.Always, false, "propertyQName",
            null, null, null, DateTime.now(), null);

    private final Method method = new Method("_mth_125698541256985478", "method", dp.getAccountId(), dp.getId(), null,
            Method.Access.Private, false, "methodQName", new MethodImpl(null, "return true;", MethodImpl.ExecType.Javascript),
            1L, property.getId(), null, DateTime.now(), null);

    @Before
    public void setUp() throws Exception {
        Connio.init("key", "password");
    }

    @Test
    public void deviceStateFetchRequestShouldPerformExpectedRequest() throws Exception {
        new Expectations() {{
            final String path = "data/devices/" + device.getId();
            final ImmutableMap<String, String> parameters = ImmutableMap.of("alerts", "true", "meta", "true", "methods",
                    method.getName(), "properties", property.getName(), "stats", "true");
            final Request request = Request.get(path, parameters);
            response.isSuccess(); result = true;
            httpClient.request(request); times = 2;
            httpClient.requestAsync(request); result = CompletableFuture.supplyAsync(() -> response); times = 2;
        }};

        final ConnioApiClient apiClient = new ConnioApiClientImpl("key", "secret");

        final DeviceStateFetchRequest request = device.state()
                .setAlerts(true)
                .setMeta(true)
                .setMethods(ImmutableList.of(method.getName()))
                .setProperties(ImmutableList.of(property.getName()))
                .setStats(true);

            request.execute();
            request.executeAsync();

            request.execute(apiClient);
            request.executeAsync(apiClient);
    }

    @Test
    public void deviceStateFetchRequestShouldReturnEmptyWhenRequestError() throws Exception {
        new Expectations() {{
            final String path = "data/devices/" + device.getId();
            final ImmutableMap<String, String> parameters = ImmutableMap.of("alerts", "true", "meta", "true", "methods",
                    method.getName(), "properties", property.getName(), "stats", "true");
            final Request request = Request.get(path, parameters);
            response.isSuccess(); result = false;
            httpClient.request(request); times = 2;
            httpClient.requestAsync(request); result = CompletableFuture.<Response>supplyAsync(() -> response); times = 2;
        }};

        final ConnioApiClient apiClient = new ConnioApiClientImpl("key", "secret");

        final DeviceStateFetchRequest request = device.state()
                .setAlerts(true)
                .setMeta(true)
                .setMethods(ImmutableList.of(method.getName()))
                .setProperties(ImmutableList.of(property.getName()))
                .setStats(true);

        Assert.assertTrue(!request.fetch().isPresent());
        Assert.assertTrue(!request.fetchAsync().get().isPresent());

        Assert.assertTrue(!request.fetch(apiClient).isPresent());
        Assert.assertTrue(!request.fetchAsync(apiClient).get().isPresent());
    }

    @Test
    public void invokePublicMethodRequestShouldPerformExpectedRequest() throws Exception {
        final Object parameter = 1;
        new Expectations() {{
            final String path = "data/devices/" + device.getId() + "/methods/" + method.getId();
            final Request request = Request.put(path, parameter);
            response.isSuccess(); result = true;
            response.readEntity(MethodResult.class); result = new MethodResult(1);
            httpClient.request(request); times = 2;
            httpClient.requestAsync(request); result = CompletableFuture.supplyAsync(() -> response); times = 2;
        }};

        final ConnioApiClient apiClient = new ConnioApiClientImpl("key", "secret");

        final ExecutePublicMethodRequest request = device.executeMethod(method).setParameter(parameter);

        request.execute();
        request.executeAsync();

        request.execute(apiClient);
        request.executeAsync(apiClient);
    }

    @Test
    public void readPublicMethodRequestShouldPerformExpectedRequest() throws Exception {
        new Expectations() {{
            final String path = "data/devices/" + device.getId() + "/methods/" + method.getId();
            final Request request = Request.get(path);
            response.isSuccess(); result = true;
            response.readEntity(MethodResult.class); result = new MethodResult(1);
            httpClient.request(request); times = 2;
            httpClient.requestAsync(request); result = CompletableFuture.supplyAsync(() -> response); times = 2;

        }};

        final ConnioApiClient apiClient = new ConnioApiClientImpl("key", "secret");

        final ReadPublicMethodRequest request = device.readMethod(method);

        request.execute();
        request.executeAsync();

        request.execute(apiClient);
        request.executeAsync(apiClient);
    }

    @Test
    public void deviceReadDataRequestShouldPerformExpectedRequest() throws Exception {
        new Expectations() {{
            final ImmutableMap<String, String> parameters = ImmutableMap.of("bookmark", "bookmark", "limit", "1", "q",
                    "value:15", "skip", "1", "sort", "asc");
            final String path = "data/devices/" + device.getId() + "/properties/" + property.getId();
            final Request request = Request.get(path, parameters);
            response.isSuccess(); result = true;
            httpClient.request(request); times = 2;
            httpClient.requestAsync(request); result = CompletableFuture.supplyAsync(() -> response); times = 2;
        }};

        final ConnioApiClient apiClient = new ConnioApiClientImpl("key", "secret");

        final ReadDataRequest request = device.readData(property)
                .setBookmark("bookmark")
                .setLimit(1L)
                .setQuery("value:15")
                .setSkip(1L)
                .setSort("asc");

        request.execute();
        request.executeAsync();

        request.execute(apiClient);
        request.executeAsync(apiClient);
    }

    @Test
    public void deviceWriteDataWithoutSpecificPropertyRequestShouldPerformExpectedRequest() throws Exception {
        final DataPoint dp = new DataPoint.Builder(15).addAnnotation("customAnn", "value").setT(DateTime.now()).build();
        final DataFeed dataFeed = new DataFeed(ImmutableMap.of("customAnn", "value"), ImmutableList.of(dp));

        new Expectations() {{
            final String path = "data/devices/" + device.getId() + "/properties";
            final Request request = Request.post(path, dataFeed);
            response.isSuccess(); result = true;
            httpClient.request(request); times = 2;
            httpClient.requestAsync(request); result = CompletableFuture.supplyAsync(() -> response); times = 2;
        }};

        final ConnioApiClient apiClient = new ConnioApiClientImpl("key", "secret");

        final WriteDataFeedRequest request = device.writeData(dataFeed);

        request.execute();
        request.executeAsync();

        request.execute(apiClient);
        request.executeAsync(apiClient);
    }

    @Test
    public void deviceWriteDataToSpecificPropertyRequestShouldPerformExpectedRequest() throws Exception {
        final DataPoint dp = new DataPoint.Builder(15).addAnnotation("customAnn", "value").setT(DateTime.now()).build();
        final DataFeed dataFeed = new DataFeed(ImmutableMap.of("customAnn", "value"), ImmutableList.of(dp));

        new Expectations() {{
            final String path = "data/devices/" + device.getId() + "/properties/" + property.getId();
            final Request request = Request.post(path, dataFeed);
            response.isSuccess(); result = true;
            httpClient.request(request); times = 2;
            httpClient.requestAsync(request); result = CompletableFuture.supplyAsync(() -> response); times = 2;
        }};

        final ConnioApiClient apiClient = new ConnioApiClientImpl("key", "secret");

        final WriteDataFeedRequest request = device.writeData(property, dataFeed);

        request.execute();
        request.executeAsync();

        request.execute(apiClient);
        request.executeAsync(apiClient);
    }
}