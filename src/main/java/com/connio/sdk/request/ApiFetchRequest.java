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
