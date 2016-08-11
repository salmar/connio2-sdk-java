package com.connio.sdk.http;

import java.util.concurrent.CompletableFuture;

/**
 * HttP client used to perform HTTP requests to the Connio backend.
 */
public interface HttpClient {

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
