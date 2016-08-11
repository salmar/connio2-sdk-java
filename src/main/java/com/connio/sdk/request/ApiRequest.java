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
import com.connio.sdk.ConnioApiClient;
import com.connio.sdk.exception.ConnioApiException;
import com.connio.sdk.http.Request;
import com.connio.sdk.http.Response;

import java.util.concurrent.CompletableFuture;

/**
 * General base abstract class from where all requests inherit. It represents a request that expect to get back and
 * object of type T in the response.
 * @param <T>
 */
public abstract class ApiRequest<T> {

    /**
     * Executes the request. Uses implicit or default ConnioApiClient, note that this needs some previous initialisation
     * step.
     * @return instance of an object of type T obtained from the server as a result of the request execution.
     */
    public T execute() {
        return execute(Connio.getApiClient());
    }

    /**
     * Executes the request in a non blocking fashion. Uses implicit or default ConnioApiClient, note that this needs
     * some previous initialisation step.
     * @return completable future of an instance of an object of type T obtained from the server as a result of the
     * request execution. It can be composed with other futures or handled without blocking.
     */
    public CompletableFuture<T> executeAsync() {
        return executeAsync(Connio.getApiClient());
    }

    /**
     * Executes the request.
     * * @param connioApiClient that will be used to perform the request.
     * @return instance of an object of type T obtained from the server as a result of the request execution.
     */
    public T execute(ConnioApiClient connioApiClient) {
        return processResponse(connioApiClient.request(request()));
    }


    /**
     * Executes the request in a non blocking fashion.
     * @param connioApiClient that will be used to perform the request.
     * @return completable future of an instance of an object of type T obtained from the server as a result of the
     * request execution. It can be composed with other futures or handled without blocking.
     */
    public CompletableFuture<T> executeAsync(ConnioApiClient connioApiClient) {
        return connioApiClient.requestAsync(request()).thenApply(this::processResponse);
    }


    protected T processResponse(Response response) {
        if (response.isSuccess()) {
            return parseEntity(response);
        } else {
            throw response.readEntity(ConnioApiException.class);
        }
    }


    /**
     * It returns the HTTP request that matches this class request.
     * @return Request object that this request class represents.
     */
    protected abstract Request request();

    /**
     * Given a response for the request this class represents should obtain an instance of the parametrized type this
     * class implements.
     * @param response
     * @return
     */
    protected abstract T parseEntity(Response response);
}