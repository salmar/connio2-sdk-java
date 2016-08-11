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

package com.connio.sdk.request.account;

import com.connio.sdk.RandomAccessResultPage;
import com.connio.sdk.http.Request;
import com.connio.sdk.http.Response;
import com.connio.sdk.request.ResourcePaginatedReadRequest;
import com.connio.sdk.resource.account.Account;
import org.apache.commons.lang3.StringUtils;

import javax.ws.rs.core.GenericType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Account read request used to read accounts and specify pagination.
 */
public class AccountReadRequest extends ResourcePaginatedReadRequest<Account> {

    private String email;

    private String name;

    private Account.Status status;

    private List<String> tags;

    public String getEmail() {
        return email;
    }

    public AccountReadRequest setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getName() {
        return name;
    }

    public AccountReadRequest setName(String name) {
        this.name = name;
        return this;
    }

    public Account.Status getStatus() {
        return status;
    }

    public AccountReadRequest setStatus(Account.Status status) {
        this.status = status;
        return this;
    }

    public List<String> getTags() {
        return tags;
    }

    public AccountReadRequest setTags(List<String> tags) {
        this.tags = tags;
        return this;
    }

    @Override
    protected Request request() {
        final Map<String, String> filter = new HashMap<>();

        if (StringUtils.isNotBlank(email)) filter.put("email", email);
        if (StringUtils.isNotBlank(name)) filter.put("name", name);
        if (status != null) filter.put("status", status.value());
        if (tags != null && tags.size() > 0) filter.put("tags", StringUtils.join(tags, ","));

        filter.putAll(getPaginationParameters());

        return Request.get("accounts", filter);
    }

    @Override
    protected RandomAccessResultPage<Account> parseEntity(Response response) {
        return response.readEntity(new GenericType<RandomAccessResultPage<Account>>(){});
    }
}
