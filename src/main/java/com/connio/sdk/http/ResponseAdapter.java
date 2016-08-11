package com.connio.sdk.http;

import javax.ws.rs.core.GenericType;

/**
 * Adapter over {@link javax.ws.rs.core.Response} to simplify it and implement domain Response interface.
 */
public class ResponseAdapter implements Response {

    private final javax.ws.rs.core.Response response;

    public ResponseAdapter(javax.ws.rs.core.Response response) {
        this.response = response;
    }

    @Override
    public <T> T readEntity(Class<T> clazz) {
        return response.readEntity(clazz);
    }

    @Override
    public <T> T readEntity(GenericType<T> entityType) {
        return response.readEntity(entityType);
    }

    @Override
    public Integer getStatusCode() {
        return response.getStatus();
    }
}
