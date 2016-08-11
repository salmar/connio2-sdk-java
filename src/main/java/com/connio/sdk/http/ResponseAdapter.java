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
