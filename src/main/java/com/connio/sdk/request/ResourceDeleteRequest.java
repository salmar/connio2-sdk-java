package com.connio.sdk.request;

import com.connio.sdk.http.Response;
import com.connio.sdk.response.DeletedResponse;

public abstract class ResourceDeleteRequest extends ApiRequest<DeletedResponse> {

    @Override
    protected DeletedResponse parseEntity(Response response) {
        return response.readEntity(DeletedResponse.class);
    }
}