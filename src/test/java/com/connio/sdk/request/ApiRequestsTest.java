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
import com.connio.sdk.exception.ConnioApiException;
import com.connio.sdk.http.Request;
import com.connio.sdk.http.Response;
import mockit.Expectations;
import mockit.Mocked;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class ApiRequestsTest {

    @Mocked
    private DefaultConnioApiClient connioApiClient;

    @Mocked
    private Response response;

    private static final Request req = Request.get("dummy/path");

    @Before
    public void setUp() throws Exception {
        Connio.init("key", "password");
    }

    @Test(expected = ConnioApiException.class)
    public void whenImplicitSyncResponseIsNotSuccessFullShouldThrowConnioApiException() throws Exception {
        new Expectations() {{
            response.readEntity(ConnioApiException.class); result = new ConnioApiException(1, "", "", "", "");
            response.isSuccess(); result = false;
        }};

        new ApiRequestTest().execute();
    }

    @Test(expected = ConnioApiException.class)
    public void whenImplicitAsyncResponseIsNotSuccessFullShouldThrowConnioApiException() throws Throwable {
        new Expectations() {{
            connioApiClient.requestAsync(req); result = CompletableFuture.completedFuture(response); times = 1;
            response.readEntity(ConnioApiException.class); result = new ConnioApiException(1, "", "", "", "");
            response.isSuccess(); result = false;
        }};

        try {
            new ApiRequestTest().executeAsync().get();
        } catch (ExecutionException e) {
            throw e.getCause();
        }
    }

    @Test(expected = ConnioApiException.class)
    public void whenExplicitSyncResponseIsNotSuccessFullShouldThrowConnioApiException() throws Exception {
        new Expectations() {{
            response.readEntity(ConnioApiException.class); result = new ConnioApiException(1, "", "", "", "");
            response.isSuccess(); result = false;
        }};

        new ApiRequestTest().execute(connioApiClient);
    }

    @Test(expected = ConnioApiException.class)
    public void whenExplicitAsyncResponseIsNotSuccessFullShouldThrowConnioApiException() throws Throwable {
        new Expectations() {{
            connioApiClient.requestAsync(req); result = CompletableFuture.completedFuture(response); times = 1;
            response.readEntity(ConnioApiException.class); result = new ConnioApiException(1, "", "", "", "");
            response.isSuccess(); result = false;
        }};

        try {
            new ApiRequestTest().executeAsync(connioApiClient).get();
        } catch (ExecutionException e) {
            throw e.getCause();
        }
    }

    @Test
    public void whenImplicitSyncRequestSuccessShouldReturnParsedEntity() {
        new Expectations() {{
            response.isSuccess(); result = true;
        }};

        Assert.assertEquals(ApiRequestTest.ENTITY_VALUE, new ApiRequestTest().execute());
    }

    @Test
    public void whenExplicitSyncRequestSuccessShouldReturnParsedEntity() throws Exception {
        new Expectations() {{
            response.isSuccess(); result = true;
        }};

        Assert.assertEquals(ApiRequestTest.ENTITY_VALUE, new ApiRequestTest().execute(connioApiClient));
    }

    @Test
    public void whenImplicitAsyncRequestSuccessShouldReturnParsedEntity() throws Exception {
        new Expectations() {{
            connioApiClient.requestAsync(req); result = CompletableFuture.completedFuture(response); times = 1;
            response.isSuccess(); result = true;
        }};

        Assert.assertEquals(ApiRequestTest.ENTITY_VALUE, new ApiRequestTest().executeAsync().get());
    }

    @Test
    public void whenExplicitAsyncRequestSuccessShouldReturnParsedEntity() throws Exception {
        new Expectations() {{
            connioApiClient.requestAsync(req); result = CompletableFuture.completedFuture(response); times = 1;
            response.isSuccess(); result = true;
        }};

        Assert.assertEquals(ApiRequestTest.ENTITY_VALUE, new ApiRequestTest().executeAsync(connioApiClient).get());
    }

    private static class ApiRequestTest extends ApiRequest<String> {

        private static final String ENTITY_VALUE = "VALUE";

        @Override
        protected Request request() {
            return req;
        }

        @Override
        protected String parseEntity(Response response) {
            return ENTITY_VALUE;
        }
    }
}