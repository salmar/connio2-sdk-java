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
import com.connio.sdk.http.Response;
import com.connio.sdk.request.ResourceCreateRequest;
import com.connio.sdk.resource.account.Account;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Objects;

/**
 * Account create request used to create new accounts.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountCreateRequest extends ResourceCreateRequest<Account> {

    private final String name;

    private final Account.Type type;

    private Account.Status status;

    private String orgName;

    private String orgWebsite;

    private String orgImageUri;


    public AccountCreateRequest(String name, Account.Type type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public Account.Type getType() {
        return type;
    }

    public Account.Status getStatus() {
        return status;
    }

    public AccountCreateRequest setStatus(Account.Status status) {
        this.status = status;
        return this;
    }

    public String getOrgName() {
        return orgName;
    }

    public AccountCreateRequest setOrgName(String orgName) {
        this.orgName = orgName;
        return this;
    }

    public String getOrgWebsite() {
        return orgWebsite;
    }

    public AccountCreateRequest setOrgWebsite(String orgWebsite) {
        this.orgWebsite = orgWebsite;
        return this;
    }

    public String getOrgImageUri() {
        return orgImageUri;
    }

    public AccountCreateRequest setOrgImageUri(String orgImageUri) {
        this.orgImageUri = orgImageUri;
        return this;
    }

    @Override
    protected Request request() {
        return Request.post("accounts", this);
    }

    @Override
    protected Account parseEntity(Response response) {
        return response.readEntity(Account.class);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountCreateRequest that = (AccountCreateRequest) o;
        return Objects.equals(getName(), that.getName()) &&
                Objects.equals(getType(), that.getType()) &&
                Objects.equals(getStatus(), that.getStatus()) &&
                Objects.equals(getOrgName(), that.getOrgName()) &&
                Objects.equals(getOrgWebsite(), that.getOrgWebsite()) &&
                Objects.equals(getOrgImageUri(), that.getOrgImageUri());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getType(), getStatus(), getOrgName(), getOrgWebsite(), getOrgImageUri());
    }
}
