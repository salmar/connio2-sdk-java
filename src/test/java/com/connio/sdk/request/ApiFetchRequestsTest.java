package com.connio.sdk.request;

import com.connio.sdk.Connio;
import com.connio.sdk.DefaultConnioApiClient;
import com.connio.sdk.http.Request;
import com.connio.sdk.http.Response;
import mockit.Expectations;
import mockit.Mocked;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ApiFetchRequestsTest {

    @Mocked
    private DefaultConnioApiClient connioApiClient;

    @Mocked
    private Response response;

    private static final Request req = Request.get("dummy/path");

    @Before
    public void setUp() throws Exception {
        Connio.init("key", "password");
    }

    @Test
    public void whenImplicitSyncResponseIsNotSuccessFullShouldThrowConnioApiException() throws Exception {
        new Expectations() {{
            response.isSuccess(); result = false;
        }};

        assertTrue(!new ApiFetchRequestTest().fetch().isPresent());
    }

    @Test
    public void whenImplicitAsyncResponseIsNotSuccessFullShouldThrowConnioApiException() throws Throwable {
        new Expectations() {{
            connioApiClient.requestAsync(req); result = CompletableFuture.completedFuture(response); times = 1;
            response.isSuccess(); result = false;
        }};

        assertTrue(!new ApiFetchRequestTest().fetchAsync().get().isPresent());
    }

    @Test
    public void whenExplicitSyncResponseIsNotSuccessFullShouldThrowConnioApiException() throws Exception {
        new Expectations() {{
            response.isSuccess(); result = false;
        }};

        assertTrue(!new ApiFetchRequestTest().fetch(connioApiClient).isPresent());
    }

    @Test
    public void whenExplicitAsyncResponseIsNotSuccessFullShouldThrowConnioApiException() throws Throwable {
        new Expectations() {{
            connioApiClient.requestAsync(req); result = CompletableFuture.completedFuture(response); times = 1;
            response.isSuccess(); result = false;
        }};

        assertTrue(!new ApiFetchRequestTest().fetchAsync(connioApiClient).get().isPresent());
    }

    @Test
    public void whenImplicitSyncRequestSuccessShouldReturnParsedEntity() {
        new Expectations() {{
            response.isSuccess(); result = true;
        }};

        assertEquals(ApiFetchRequestTest.ENTITY_VALUE, new ApiFetchRequestTest().fetch().get());
    }

    @Test
    public void whenExplicitSyncRequestSuccessShouldReturnParsedEntity() throws Exception {
        new Expectations() {{
            response.isSuccess(); result = true;
        }};

        assertEquals(ApiFetchRequestTest.ENTITY_VALUE, new ApiFetchRequestTest().fetch(connioApiClient).get());
    }

    @Test
    public void whenImplicitAsyncRequestSuccessShouldReturnParsedEntity() throws Exception {
        new Expectations() {{
            connioApiClient.requestAsync(req); result = CompletableFuture.completedFuture(response); times = 1;
            response.isSuccess(); result = true;
        }};

        assertEquals(ApiFetchRequestTest.ENTITY_VALUE, new ApiFetchRequestTest().fetchAsync().get().get());
    }

    @Test
    public void whenExplicitAsyncRequestSuccessShouldReturnParsedEntity() throws Exception {
        new Expectations() {{
            connioApiClient.requestAsync(req); result = CompletableFuture.completedFuture(response); times = 1;
            response.isSuccess(); result = true;
        }};

        assertEquals(ApiFetchRequestTest.ENTITY_VALUE, new ApiFetchRequestTest().fetchAsync(connioApiClient).get().get());
    }

    private static class ApiFetchRequestTest extends ApiFetchRequest<String> {

        private static final String ENTITY_VALUE = "VALUE";

        @Override
        protected Request request() {
            return req;
        }

        @Override
        protected String parseResourceEntity(Response response) {
            return ENTITY_VALUE;
        }
    }
}