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

package com.connio.sdk.request.apiclient;

import com.connio.sdk.RandomAccessResultPage;
import com.connio.sdk.http.Request;
import com.connio.sdk.http.Response;
import com.connio.sdk.request.ResourcePaginatedReadRequest;
import com.connio.sdk.resource.apiclient.ApiClient;

import javax.ws.rs.core.GenericType;

/**
 * Api client read request used to read api clients and specify pagination.
 */
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
