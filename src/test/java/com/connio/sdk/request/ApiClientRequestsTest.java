package com.connio.sdk.request;

import com.connio.sdk.Connio;
import com.connio.sdk.ConnioApiClient;
import com.connio.sdk.ConnioApiClientImpl;
import com.connio.sdk.auth.ApiKeyContext;
import com.connio.sdk.auth.ApiKeyScope;
import com.connio.sdk.http.JerseyClient;
import com.connio.sdk.http.Request;
import com.connio.sdk.http.Response;
import com.connio.sdk.request.account.AccountAddRequest;
import com.connio.sdk.request.account.AccountDeleteRequest;
import com.connio.sdk.request.account.AccountUpdateRequest;
import com.connio.sdk.request.apiclient.ApiClientAddRequest;
import com.connio.sdk.request.apiclient.ApiClientDeleteRequest;
import com.connio.sdk.request.apiclient.ApiClientUpdateRequest;
import com.connio.sdk.resource.account.Account;
import com.connio.sdk.resource.apiclient.ApiClient;
import com.google.common.collect.ImmutableSet;
import mockit.Expectations;
import mockit.Mocked;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;

public class ApiClientRequestsTest {

    @Mocked
    private JerseyClient httpClient;

    @Mocked
    private Response response;

    private final ApiKeyContext context = new ApiKeyContext(ApiKeyContext.Type.Account, ImmutableSet.of("_acc_125632547856985412"));

    private final ImmutableSet<ApiKeyScope> scope = ImmutableSet.of(ApiKeyScope.APICLIENT_CREATE);

    private final ApiClient apiClient = new ApiClient("_api_125632587415236985", "api client", "_acc_125632547856985412",
            "description", ImmutableSet.of("tag1"), DateTime.now(), null);

    @Before
    public void setUp() throws Exception {
        Connio.init("key", "password");
    }

    @Test
    public void addRequestShouldPerformExpectedRequest() throws Exception {
        new Expectations() {{
            final ApiClientAddRequest addRequest = new ApiClientAddRequest(context, scope)
                    .setDescription(apiClient.getDescription().orElse(""))
                    .setTags(apiClient.getTags())
                    .setRateLimit("1m");

            final Request request = Request.post("apiclients", addRequest);
            response.isSuccess(); result = true;
            httpClient.request(request); times = 2;
            httpClient.requestAsync(request); result = CompletableFuture.supplyAsync(() -> response); times = 2;
        }};

        final ApiClientAddRequest request = ApiClient.create(context, scope)
                .setDescription(apiClient.getDescription().orElse(""))
                .setTags(apiClient.getTags())
                .setRateLimit("1m");

            request.execute();
            request.executeAsync();

            final ConnioApiClient apiClient = new ConnioApiClientImpl("key", "secret");
            request.execute(apiClient);
            request.executeAsync(apiClient);
    }

    @Test
    public void updateRequestShouldPerformExpectedRequest() throws Exception {
        new Expectations() {{
            final ApiClientUpdateRequest updateRequest = new ApiClientUpdateRequest(apiClient)
                    .setContext(context)
                    .setDescription(apiClient.getDescription().orElse(""))
                    .setRateLimit("1m")
                    .setScope(scope)
                    .setTags(apiClient.getTags());

            final String path = "apiclients/" + apiClient.getId();
            final Request request = Request.put(path, updateRequest);
            response.isSuccess(); result = true;
            httpClient.request(request); times = 2;
            httpClient.requestAsync(request); result = CompletableFuture.supplyAsync(() -> response); times = 2;
        }};

        final ApiClientUpdateRequest request = apiClient.update()
                .setContext(context)
                .setDescription(apiClient.getDescription().orElse(""))
                .setRateLimit("1m")
                .setScope(scope)
                .setTags(apiClient.getTags());

        request.execute();
        request.executeAsync();

        final ConnioApiClient apiClient = new ConnioApiClientImpl("key", "secret");
        request.execute(apiClient);
        request.executeAsync(apiClient);
    }

    @Test
    public void deleteRequestShouldPerformExpectedRequest() throws Exception {
        new Expectations() {{
            final String path = "apiclients/" + apiClient.getId();
            final Request request = Request.delete(path);
            response.isSuccess(); result = true;
            httpClient.request(request); times = 2;
            httpClient.requestAsync(request); result = CompletableFuture.supplyAsync(() -> response); times = 2;
        }};

        final ApiClientDeleteRequest request = apiClient.delete();

        request.execute();
        request.executeAsync();

        final ConnioApiClient apiClient = new ConnioApiClientImpl("key", "secret");
        request.execute(apiClient);
        request.executeAsync(apiClient);
    }
}