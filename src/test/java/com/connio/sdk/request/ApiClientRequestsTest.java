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
import com.connio.sdk.auth.ApiKeyContext;
import com.connio.sdk.auth.ApiKeyScope;
import com.connio.sdk.http.Request;
import com.connio.sdk.http.Response;
import com.connio.sdk.request.apiclient.ApiClientCreateRequest;
import com.connio.sdk.request.apiclient.ApiClientDeleteRequest;
import com.connio.sdk.request.apiclient.ApiClientUpdateRequest;
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
    private DefaultConnioApiClient connioApiClient;

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
            final ApiClientCreateRequest addRequest = new ApiClientCreateRequest(context, scope)
                    .setDescription(apiClient.getDescription().orElse(""))
                    .setTags(apiClient.getTags())
                    .setRateLimit("1m");

            final Request request = Request.post("apiclients", addRequest);
            response.isSuccess(); result = true;
            connioApiClient.request(request); times = 2;
            connioApiClient.requestAsync(request); result = CompletableFuture.completedFuture(response); times = 2;
        }};

        final ApiClientCreateRequest request = ApiClient.create(context, scope)
                .setDescription(apiClient.getDescription().orElse(""))
                .setTags(apiClient.getTags())
                .setRateLimit("1m");

            request.execute();
            request.executeAsync();

            request.execute(connioApiClient);
            request.executeAsync(connioApiClient);
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
            connioApiClient.request(request); times = 2;
            connioApiClient.requestAsync(request); result = CompletableFuture.completedFuture(response); times = 2;
        }};

        final ApiClientUpdateRequest request = apiClient.update()
                .setContext(context)
                .setDescription(apiClient.getDescription().orElse(""))
                .setRateLimit("1m")
                .setScope(scope)
                .setTags(apiClient.getTags());

        request.execute();
        request.executeAsync();

        request.execute(connioApiClient);
        request.executeAsync(connioApiClient);
    }

    @Test
    public void deleteRequestShouldPerformExpectedRequest() throws Exception {
        new Expectations() {{
            final String path = "apiclients/" + apiClient.getId();
            final Request request = Request.delete(path);
            response.isSuccess(); result = true;
            connioApiClient.request(request); times = 2;
            connioApiClient.requestAsync(request); result = CompletableFuture.completedFuture(response); times = 2;
        }};

        final ApiClientDeleteRequest request = apiClient.delete();

        request.execute();
        request.executeAsync();

        request.execute(connioApiClient);
        request.executeAsync(connioApiClient);
    }
}