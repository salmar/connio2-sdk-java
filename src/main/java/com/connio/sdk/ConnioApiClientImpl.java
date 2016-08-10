package com.connio.sdk;

import com.connio.sdk.http.HttpClient;
import com.connio.sdk.http.JerseyClient;
import com.connio.sdk.http.Request;
import com.connio.sdk.http.Response;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConnioApiClientImpl implements ConnioApiClient {

    private final HttpClient httpClient;

    private ExecutorService executorService;

    private boolean dedicatedExecutor = false;

    
    public ConnioApiClientImpl(String key, String secret) throws Exception {
        this(key, secret, Executors.newCachedThreadPool());
        this.dedicatedExecutor = true;
    }

    public ConnioApiClientImpl(String key, String secret, ExecutorService executorService) throws Exception {
        this(new JerseyClient(key, secret, "https://api.connio.com/v2", executorService));
        this.executorService = executorService;
    }

    public ConnioApiClientImpl(String key, String secret, String endpoint, ExecutorService executorService) throws Exception {
        this(new JerseyClient(key, secret, endpoint, executorService));
        this.executorService = executorService;
    }

    public ConnioApiClientImpl(HttpClient httpClient) throws Exception {
        this.httpClient = httpClient;
    }

    public void terminate() {
        if (dedicatedExecutor) executorService.shutdown();
    }

    @Override
    public Response request(Request request) {
        return httpClient.request(request);
    }

    @Override
    public CompletableFuture<Response> requestAsync(Request request) {
        return httpClient.requestAsync(request);
    }
}