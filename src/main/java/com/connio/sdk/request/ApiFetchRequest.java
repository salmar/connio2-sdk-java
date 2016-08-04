package com.connio.sdk.request;

import com.connio.sdk.ConnioApiClient;
import com.connio.sdk.http.Response;
import com.connio.sdk.resource.Resource;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public abstract class ApiFetchRequest<T> extends ApiRequest<Optional<T>> {

    public Optional<T> fetch() {
        return execute();
    }

    public Optional<T> fetch(ConnioApiClient apiClient) {
        return execute(apiClient);
    }

    public CompletableFuture<Optional<T>> fetchAsync() {
        return executeAsync();
    }

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
        return Optional.of(parseResourceEntity(response));
    }

    protected abstract T parseResourceEntity(Response response);
}
