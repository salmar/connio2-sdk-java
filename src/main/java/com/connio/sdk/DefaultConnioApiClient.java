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

package com.connio.sdk;

import com.connio.sdk.http.HttpClient;
import com.connio.sdk.http.JerseyClient;
import com.connio.sdk.http.Request;
import com.connio.sdk.http.Response;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DefaultConnioApiClient implements ConnioApiClient {

    private final HttpClient httpClient;

    private ExecutorService executorService;

    private boolean dedicatedExecutor = false;

    
    public DefaultConnioApiClient(String key, String secret) throws Exception {
        this(key, secret, Executors.newCachedThreadPool());
        this.dedicatedExecutor = true;
    }

    public DefaultConnioApiClient(String key, String secret, ExecutorService executorService) throws Exception {
        this(new JerseyClient(key, secret, "https://api.connio.com/v2", executorService));
        this.executorService = executorService;
    }

    public DefaultConnioApiClient(String key, String secret, String endpoint, ExecutorService executorService) throws Exception {
        this(new JerseyClient(key, secret, endpoint, executorService));
        this.executorService = executorService;
    }

    public DefaultConnioApiClient(HttpClient httpClient) throws Exception {
        this.httpClient = httpClient;
    }

    public void terminate() {
        if (dedicatedExecutor) executorService.shutdown();
    }

    @Override
    public Response request(Request request) {
        return httpClient.request(request);
    }

    @Override
    public CompletableFuture<Response> requestAsync(Request request) {
        return httpClient.requestAsync(request);
    }
}