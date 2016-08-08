package com.connio.sdk;

import com.connio.sdk.http.Request;
import com.connio.sdk.http.Response;

import java.util.concurrent.CompletableFuture;

public interface ConnioApiClient {

    Response request(Request request);

    CompletableFuture<Response> requestAsync(Request request);
}
