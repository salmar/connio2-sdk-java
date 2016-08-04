package com.connio.sdk.request.apiclient;

import com.connio.sdk.RandomAccessResultPage;
import com.connio.sdk.http.Request;
import com.connio.sdk.http.Response;
import com.connio.sdk.request.ResourcePaginatedReadRequest;
import com.connio.sdk.resource.apiclient.ApiClient;

import javax.ws.rs.core.GenericType;

public class ApiClientReadRequest extends ResourcePaginatedReadRequest<ApiClient> {

    @Override
    public Request request() {
        return Request.get("apiclients", getPaginationParameters());
    }

    @Override
    protected RandomAccessResultPage<ApiClient> parseEntity(Response response) {
        return response.readEntity(new GenericType<RandomAccessResultPage<ApiClient>>(){});
    }
}
