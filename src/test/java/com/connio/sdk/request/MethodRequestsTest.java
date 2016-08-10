package com.connio.sdk.request;

import com.connio.sdk.Connio;
import com.connio.sdk.ConnioApiClient;
import com.connio.sdk.ConnioApiClientImpl;
import com.connio.sdk.http.JerseyClient;
import com.connio.sdk.http.Request;
import com.connio.sdk.http.Response;
import com.connio.sdk.request.alert.AlertDeleteRequest;
import com.connio.sdk.request.method.MethodAddRequest;
import com.connio.sdk.request.method.MethodDeleteRequest;
import com.connio.sdk.request.method.MethodUpdateRequest;
import com.connio.sdk.resource.deviceprofile.DeviceProfile;
import com.connio.sdk.resource.method.Method;
import com.connio.sdk.resource.method.MethodImpl;
import com.google.common.collect.ImmutableSet;
import mockit.Expectations;
import mockit.Mocked;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;

public class MethodRequestsTest {

    @Mocked
    private JerseyClient httpClient;

    @Mocked
    private Response response;

    private final DeviceProfile dp = new DeviceProfile("_dpf_125632547856985412", "dp", "_acc_1256985412563258", null,
            "description", null, null, null, null, ImmutableSet.<String>of(), DateTime.now(), null);

    private final Method method = new Method("_mth_125695874512365258", "method", dp.getAccountId(), dp.getId(), null,
            Method.Access.Private, false, "methodQName", new MethodImpl(null, "return true;", MethodImpl.ExecType.Javascript),
            1L, "_prp_125632547852369854", "_prp_1259854563258745632", DateTime.now(), null);


    @Before
    public void setUp() throws Exception {
        Connio.init("key", "password");
    }

    @Test
    public void methodCreationShouldPerformExpectedRequest() throws Exception {
        new Expectations() {{
            final MethodAddRequest addRequest = new MethodAddRequest(dp, method.getName(), method.getAccess(), method.getMethodImpl())
                    .setInputId(method.getInputId().orElse(""))
                    .setInputPropTTL(method.getInputPropTTL().orElse(null))
                    .setOutputId(method.getOutputId().orElse(""));


            final String path = "deviceprofiles/" + dp.getId() + "/methods";
            final Request request = Request.post(path, addRequest);
            response.isSuccess(); result = true;
            httpClient.request(request); times = 4;
            httpClient.requestAsync(request); result = CompletableFuture.supplyAsync(() -> response); times = 4;
        }};

        final ConnioApiClient apiClient = new ConnioApiClientImpl("key", "secret");

        final MethodAddRequest request = Method.create(dp, method.getName(), method.getAccess(), method.getMethodImpl())
                .setInputId(method.getInputId().orElse(""))
                .setInputPropTTL(method.getInputPropTTL().orElse(null))
                .setOutputId(method.getOutputId().orElse(""));

            request.execute();
            request.executeAsync();

            request.execute(apiClient);
            request.executeAsync(apiClient);

        final MethodAddRequest request2 = dp.addMethod(method.getName(), method.getAccess(), method.getMethodImpl())
                .setInputId(method.getInputId().orElse(""))
                .setInputPropTTL(method.getInputPropTTL().orElse(null))
                .setOutputId(method.getOutputId().orElse(""));

        request2.execute();
        request2.executeAsync();

        request2.execute(apiClient);
        request2.executeAsync(apiClient);
    }

    @Test(expected=IllegalArgumentException.class)
    public void updateRequestShouldReturnIllegalArgumentExceptionWhenInvalidOwnerId() throws Exception {
        new MethodUpdateRequest("wrong_owner_Id", method.getId());
    }

    @Test
    public void methodUpdateRequestShouldPerformExpectedRequest() throws Exception {
        new Expectations() {{
            final MethodUpdateRequest updateRequest = new MethodUpdateRequest(dp, method.getId())
                    .setInputId(method.getInputId().orElse(""))
                    .setInputPropTTL(method.getInputPropTTL().orElse(null))
                    .setMethodImpl(method.getMethodImpl())
                    .setName(method.getName())
                    .setOutputId(method.getOutputId().orElse(""));

            final String path = "deviceprofiles/" + dp.getId() + "/methods/" + method.getId();
            final Request request = Request.put(path, updateRequest);
            response.isSuccess(); result = true;
            httpClient.request(request); times = 2;
            httpClient.requestAsync(request); result = CompletableFuture.supplyAsync(() -> response); times = 2;
        }};

        final ConnioApiClient apiClient = new ConnioApiClientImpl("key", "secret");

        final MethodUpdateRequest request = method.update()
                .setInputId(method.getInputId().orElse(""))
                .setInputPropTTL(method.getInputPropTTL().orElse(null))
                .setMethodImpl(method.getMethodImpl())
                .setName(method.getName())
                .setOutputId(method.getOutputId().orElse(""));

        request.execute();
        request.executeAsync();

        request.execute(apiClient);
        request.executeAsync(apiClient);
    }

    @Test
    public void methodDeleteRequestShouldPerformExpectedRequest() throws Exception {
        new Expectations() {{
            final String path = "deviceprofiles/" + dp.getId() + "/methods/" + method.getId();
            final Request request = Request.delete(path);
            response.isSuccess(); result = true;
            httpClient.request(request); times = 2;
            httpClient.requestAsync(request); result = CompletableFuture.supplyAsync(() -> response); times = 2;
        }};

        final ConnioApiClient apiClient = new ConnioApiClientImpl("key", "secret");
        final MethodDeleteRequest request = method.delete();

        request.execute();
        request.executeAsync();

        request.execute(apiClient);
        request.executeAsync(apiClient);
    }

    @Test(expected=IllegalArgumentException.class)
    public void deleteRequestShouldReturnIllegalArgumentExceptionWhenInvalidOwnerId() {
        new AlertDeleteRequest("wrong_owner_Id", method.getId());
    }
}