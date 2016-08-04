package com.connio.sdk.request.apiclient;

import com.connio.sdk.request.ResourceFetchRequest;
import com.connio.sdk.http.Request;
import com.connio.sdk.http.Response;
import com.connio.sdk.resource.apiclient.ApiClient;

public class ApiClientFetchRequest extends ResourceFetchRequest<ApiClient> {

    private final String id;

    public ApiClientFetchRequest(String id) {
        this.id = id;
    }

    @Override
    public Request request() {
        final String path = "apiclients/" + id;
        return Request.get(path);
    }

    @Override
    protected ApiClient parseResourceEntity(Response response) {
        return response.readEntity(ApiClient.class);
    }
}
