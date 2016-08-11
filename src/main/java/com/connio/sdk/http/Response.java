/**
 * Copyright (c) 2016 Connio Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Authors: juan@connio.com
 *
 */

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
