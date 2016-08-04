package com.connio.sdk.request.apiclient;

import com.connio.sdk.request.ResourceDeleteRequest;
import com.connio.sdk.http.Request;

public class ApiClientDeleteRequest extends ResourceDeleteRequest {

    private final String id;

    public ApiClientDeleteRequest(String id) {
        this.id = id;
    }

    @Override
    protected Request request() {
        final String path = "apiclients/" + id;
        return Request.delete(path);
    }
}
