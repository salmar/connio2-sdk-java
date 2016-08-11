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
