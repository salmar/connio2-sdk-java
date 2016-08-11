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

import com.connio.sdk.ConnioApiClient;
import com.connio.sdk.http.Response;
import com.connio.sdk.resource.Resource;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * Base class for those API request that fetches resources.
 * @param <T>
 */
public abstract class ApiFetchRequest<T> extends ApiRequest<Optional<T>> {

    /**
     * Alias of {@code execute()} to make the api more fluent.
     * @return Optional that contains the response entity in case the response is successful and empty otherwise.
     */
    public Optional<T> fetch() {
        return execute();
    }

    /**
     * Alias of {@code execute(ConnioApiClient apiClient)} to make the api more fluent.
     * @return Optional that contains the response entity in case the response is successful and empty otherwise.
     */
    public Optional<T> fetch(ConnioApiClient apiClient) {
        return execute(apiClient);
    }

    /**
     * Alias of {@code executeAsync()} to make the api more fluent.
     * @return Completable future of Optional that contains the response entity in case the response is successful
     * and empty otherwise. Non blocking operation.
     */
    public CompletableFuture<Optional<T>> fetchAsync() {
        return executeAsync();
    }

    /**
     * Alias of {@code executeAsync(ConnioApiClient apiClient)} to make the api more fluent.
     * @return Completable future of Optional that contains the response entity in case the response is successful
     * and empty otherwise. Non blocking operation.
     */
    public CompletableFuture<Optional<T>> fetchAsync(ConnioApiClient apiClient) {
        return executeAsync(apiClient);
    }


    @Override
    protected Optional<T> processResponse(Response response) {
        if (response.isSuccess()) {
            return parseEntity(response);
        } else {
            return Optional.empty();
        }
    }

    @Override
    protected Optional<T> parseEntity(Response response) {
        return Optional.ofNullable(parseResourceEntity(response));
    }

    /**
     * Abstract method that child classes must implement. This method will be executed only if the response is successful.
     * @param response HTTP response resulted from fetch / execute requests.
     * @return T object instance of the parametrised type.
     */
    protected abstract T parseResourceEntity(Response response);
}
