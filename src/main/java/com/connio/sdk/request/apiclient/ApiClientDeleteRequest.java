package com.connio.sdk.request.apiclient;

import com.connio.sdk.request.ResourceDeleteRequest;
import com.connio.sdk.http.Request;
import com.connio.sdk.resource.apiclient.ApiClient;

public class ApiClientDeleteRequest extends ResourceDeleteRequest {

    private final ApiClient apiClient;

    public ApiClientDeleteRequest(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    @Override
    protected Request request() {
        final String path = "apiclients/" + apiClient.getId();
        return Request.delete(path);
    }
}
