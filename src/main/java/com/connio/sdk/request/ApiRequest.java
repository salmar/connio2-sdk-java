package com.connio.sdk.request;

import com.connio.sdk.Connio;
import com.connio.sdk.ConnioApiClient;
import com.connio.sdk.exception.ConnioApiException;
import com.connio.sdk.http.Request;
import com.connio.sdk.http.Response;

import java.util.concurrent.CompletableFuture;

public abstract class ApiRequest<T> {

    public T execute() {
        return execute(Connio.getApiClient());
    }


    public CompletableFuture<T> executeAsync() {
        return executeAsync(Connio.getApiClient());
    }


    public T execute(ConnioApiClient connioApiClient) {
        return processResponse(connioApiClient.request(request()));
    }


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


    protected abstract Request request();


    protected abstract T parseEntity(Response response);
}