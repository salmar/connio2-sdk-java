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

import com.connio.sdk.http.Request;
import com.connio.sdk.http.Response;

import java.util.concurrent.CompletableFuture;

/**
 * Client to perform requests to Connio public API both sync and async way.
 */
public interface ConnioApiClient {

    /**
     * Given a Request it performs it and return the server response.
     * @param request Request to be executed
     * @return response where status and entity unmarshalling can be performed
     */
    Response request(Request request);

    /**
     * Async version of the method request. It performs the request and returns a completable future without blocking.
     * @param request Request to be executed
     * @return completable future of a response that can be composed. Non blocking operation.
     */
    CompletableFuture<Response> requestAsync(Request request);
}
