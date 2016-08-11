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

package com.connio.sdk.request.user;

import com.connio.sdk.RandomAccessResultPage;
import com.connio.sdk.http.Request;
import com.connio.sdk.http.Response;
import com.connio.sdk.request.ResourcePaginatedReadRequest;
import com.connio.sdk.resource.account.Account;
import org.apache.commons.lang3.StringUtils;

import javax.ws.rs.core.GenericType;
import java.util.HashMap;
import java.util.Map;

/**
 * Alert read request used to read alerts and specify pagination.
 */
public class UserReadRequest extends ResourcePaginatedReadRequest<Account> {

    private String email;

    public String getEmail() {
        return email;
    }

    public UserReadRequest setEmail(String email) {
        this.email = email;
        return this;
    }

    @Override
    protected Request request() {
        final Map<String, String> filter = new HashMap<>();

        if (StringUtils.isNotBlank(email)) filter.put("email", email);

        filter.putAll(getPaginationParameters());

        return Request.get("accounts", filter);
    }

    @Override
    protected RandomAccessResultPage<Account> parseEntity(Response response) {
        return response.readEntity(new GenericType<RandomAccessResultPage<Account>>(){});
    }
}
