package com.connio.sdk.http;

import javax.ws.rs.core.GenericType;

public interface Response {

    <T> T readEntity(Class<T> T);

    <T> T readEntity(GenericType<T> entityType);

    Integer getStatusCode();

    default boolean isSuccess() {
        final Integer status = getStatusCode();
        return status != null && status >= 200 && status < 300;
    }
}
