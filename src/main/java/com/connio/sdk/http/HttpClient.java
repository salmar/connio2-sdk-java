package com.connio.sdk.http;

import java.util.concurrent.CompletableFuture;

public interface HttpClient {

    Response request(Request request);

    CompletableFuture<Response> requestAsync(Request request);
}
