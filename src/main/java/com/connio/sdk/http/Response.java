package com.connio.sdk.http;

import javax.ws.rs.core.GenericType;

/**
 * HTTP Response.
 */
public interface Response {

    /**
     * If possible unmarshall response entity into an object which its class is specified as parameter.
     * @param T class of object the entity wants to be unmarshalled to.
     * @return T instance of the object
     */
    <T> T readEntity(Class<T> T);

    /**
     * Analogous to {@code readEntity(Class<T> T)} but to be used with generic types.
     * @param entityType generic class of object the entity wants to be unmarshalled to.
     * @return T instance of the object.
     */
    <T> T readEntity(GenericType<T> entityType);

    /**
     * Get the integer status code of the response.
     * @return integer value of the response status code.
     */
    Integer getStatusCode();

    /**
     * Helper method to verify whether the response is successfull or not.
     * @return true if the response if successfull false otherwise.
     */
    default boolean isSuccess() {
        final Integer status = getStatusCode();
        return status != null && status >= 200 && status < 300;
    }
}
