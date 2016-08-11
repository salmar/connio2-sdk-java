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

import com.connio.sdk.http.Request;
import com.connio.sdk.request.ResourceDeleteRequest;
import com.connio.sdk.resource.account.Account;

import java.util.Objects;

/**
 * Account delete request used to delete accounts.
 */
public class AccountDeleteRequest extends ResourceDeleteRequest {

    private final Account account;

    public AccountDeleteRequest(Account account) {
        this.account = account;
    }

    @Override
    protected Request request() {
        final String path = "accounts/" + account.getId();
        return Request.delete(path);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountDeleteRequest that = (AccountDeleteRequest) o;
        return Objects.equals(account, that.account);
    }

    @Override
    public int hashCode() {
        return Objects.hash(account);
    }
}
