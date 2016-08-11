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

package com.connio.sdk.http;

import com.connio.sdk.ConnioApiClient;
import com.connio.sdk.DefaultConnioApiClient;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.ImmutableMap;
import com.xebialabs.restito.server.StubServer;
import org.glassfish.grizzly.http.util.HttpStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Executors;

import static com.xebialabs.restito.builder.stub.StubHttp.whenHttp;
import static com.xebialabs.restito.builder.verify.VerifyHttp.verifyHttp;
import static com.xebialabs.restito.semantics.Action.*;
import static com.xebialabs.restito.semantics.Condition.*;
import static org.junit.Assert.assertEquals;

public class ConnioClientImplTest {

    private StubServer server;

    private ConnioApiClient connioApiClient;

    @Before
    public void start() throws Exception {
        server = new StubServer(8080).run();
        connioApiClient = new DefaultConnioApiClient("key", "password", "http://localhost:8080/v2", Executors.newCachedThreadPool());
    }

    @After
    public void stop() {
        server.stop();
    }

    @Test
    public void testGetSyncRequestWithoutQueryParams() {
        final String path = "testentity/1";
        final Request  request = Request.get(path);
        final TestEntity entity = new TestEntity(1, "X");

        whenHttp(server).match(get("/v2/" + path)).then(contentType("application/json"), stringContent(entity.toJson()));

        final Response response = connioApiClient.request(request);

        verifyHttp(server).once(basicAuth("key", "password"), get("/v2/testentity/1"));
        assertEquals(entity, response.readEntity(TestEntity.class));
    }

    @Test
    public void testGetAsyncRequestWithoutQueryParams() throws Exception {
        final String path = "testentity/1";
        final Request  request = Request.get(path);
        final TestEntity entity = new TestEntity(1, "X");

        whenHttp(server).match(get("/v2/" + path)).then(contentType("application/json"), stringContent(entity.toJson()));

        final Response response = connioApiClient.requestAsync(request).get();

        verifyHttp(server).once(basicAuth("key", "password"), get("/v2/testentity/1"));
        assertEquals(entity, response.readEntity(TestEntity.class));
    }

    @Test
    public void testGetSyncRequestWithQueryParams() throws Exception {
        final String path = "testentity/1";
        final Map<String, String> params = ImmutableMap.of("param1", "value1", "param2", "value2");
        final Request  request = Request.get(path, params);
        final TestEntity entity = new TestEntity(1, "X");

        whenHttp(server).match(get("/v2/" + path)).then(contentType("application/json"), stringContent(entity.toJson()));

        final Response response = connioApiClient.request(request);

        verifyHttp(server).once(basicAuth("key", "password"), get("/v2/testentity/1"), parameter("param1", "value1"),
                parameter("param2", "value2"));

        assertEquals(entity, response.readEntity(TestEntity.class));
    }

    @Test
    public void testGetAsyncRequestWithQueryParams() throws Exception {
        final String path = "testentity/1";
        final Map<String, String> params = ImmutableMap.of("param1", "value1", "param2", "value2");
        final Request  request = Request.get(path, params);
        final TestEntity entity = new TestEntity(1, "X");

        whenHttp(server).match(get("/v2/" + path)).then(contentType("application/json"), stringContent(entity.toJson()));

        final Response response = connioApiClient.requestAsync(request).get();

        verifyHttp(server).once(basicAuth("key", "password"), get("/v2/testentity/1"), parameter("param1", "value1"),
                parameter("param2", "value2"));

        assertEquals(entity, response.readEntity(TestEntity.class));
    }

    @Test
    public void getShouldReturnOriginalResponseStatus() {
        final String path = "testentity/1";
        final Request  request = Request.get(path);

        whenHttp(server).match(get("/v2/" + path)).then(status(HttpStatus.BAD_GATEWAY_502));

        final Response response = connioApiClient.request(request);

        verifyHttp(server).once(basicAuth("key", "password"), get("/v2/testentity/1"));

        assertEquals(response.getStatusCode(), Integer.valueOf(502));
    }

    @Test
    public void testPostSyncRequest() throws Exception {
        final String path = "testentity";
        final TestEntity entity = new TestEntity(1, "X");
        final Request  request = Request.post(path, entity);

        whenHttp(server).match(post("/v2/" + path)).then(contentType("application/json"), stringContent(entity.toJson()));

        final Response response = connioApiClient.request(request);

        verifyHttp(server).once(basicAuth("key", "password"), post("/v2/testentity"), withPostBodyContaining(entity.toJson()));
        assertEquals(entity, response.readEntity(TestEntity.class));
    }

    @Test
    public void testPostAsyncRequest() throws Exception {
        final String path = "testentity";
        final TestEntity entity = new TestEntity(1, "X");
        final Request  request = Request.post(path, entity);

        whenHttp(server).match(post("/v2/" + path)).then(contentType("application/json"), stringContent(entity.toJson()));

        final Response response = connioApiClient.requestAsync(request).get();

        verifyHttp(server).once(basicAuth("key", "password"), post("/v2/testentity"), withPostBodyContaining(entity.toJson()));
        assertEquals(entity, response.readEntity(TestEntity.class));
    }

    @Test
    public void postShouldReturnOriginalResponseStatus() {
        final String path = "testentity";
        final TestEntity entity = new TestEntity(1, "X");
        final Request  request = Request.post(path, entity);

        whenHttp(server).match(post("/v2/" + path)).then(status(HttpStatus.BAD_REQUEST_400));

        final Response response = connioApiClient.request(request);

        verifyHttp(server).once(basicAuth("key", "password"), post("/v2/testentity"), withPostBodyContaining(entity.toJson()));
        assertEquals(response.getStatusCode(), Integer.valueOf(400));
    }

    @Test
    public void testPutAsyncRequest() throws Exception {
        final String path = "testentity/1";
        final TestEntity entity = new TestEntity(1, "X");
        final Request  request = Request.put(path, entity);

        whenHttp(server).match(put("/v2/" + path)).then(contentType("application/json"), stringContent(entity.toJson()));

        final Response response = connioApiClient.requestAsync(request).get();

        verifyHttp(server).once(basicAuth("key", "password"), put("/v2/testentity/1"), withPostBodyContaining(entity.toJson()));
        assertEquals(entity, response.readEntity(TestEntity.class));
    }

    @Test
    public void testPutSyncRequest() throws Exception {
        final String path = "testentity/1";
        final TestEntity entity = new TestEntity(1, "X");
        final Request  request = Request.put(path, entity);

        whenHttp(server).match(put("/v2/" + path)).then(contentType("application/json"), stringContent(entity.toJson()));

        final Response response = connioApiClient.request(request);

        verifyHttp(server).once(basicAuth("key", "password"), put("/v2/testentity/1"), withPostBodyContaining(entity.toJson()));
        assertEquals(entity, response.readEntity(TestEntity.class));
    }

    @Test
    public void testDeleteSyncRequest() throws Exception {
        final String path = "testentity/1";
        final Request  request = Request.delete(path);

        connioApiClient.request(request);

        verifyHttp(server).once(basicAuth("key", "password"), delete("/v2/testentity/1"));
    }

    @Test
    public void testDeleteAsyncRequest() throws Exception {
        final String path = "testentity/1";
        final Request  request = Request.delete(path);

        connioApiClient.requestAsync(request).get();

        verifyHttp(server).once(basicAuth("key", "password"), delete("/v2/testentity/1"));
    }

    @Test
    public void deleteShouldReturnOriginalResponseStatus() {
        final String path = "testentity/1";
        final Request  request = Request.delete(path);

        whenHttp(server).match(get("/v2/" + path)).then(status(HttpStatus.NOT_FOUND_404));

        final Response response = connioApiClient.request(request);

        verifyHttp(server).once(basicAuth("key", "password"), delete("/v2/testentity/1"));

        assertEquals(response.getStatusCode(), Integer.valueOf(404));
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class TestEntity {

        private final Integer id;

        private final String name;

        @JsonCreator
        public TestEntity(@JsonProperty("id") Integer id, @JsonProperty("name") String name) {
            this.id = id;
            this.name = name;
        }

        public Integer getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @JsonIgnore
        public String toJson() {
            return "{\"id\":"+ id +",\"name\":\""+ name +"\"}";
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            TestEntity that = (TestEntity) o;
            return Objects.equals(getId(), that.getId()) &&
                    Objects.equals(getName(), that.getName());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getId(), getName());
        }
    }
}
