package com.connio.sdk.request.account;

import com.connio.sdk.request.ResourceUpdateRequest;
import com.connio.sdk.http.Request;
import com.connio.sdk.http.Response;
import com.connio.sdk.resource.account.Account;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.common.collect.ImmutableSet;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountUpdateRequest extends ResourceUpdateRequest<Account> {

    private final String accountId;

    private String name;

    private Account.Status status;

    private Account.Type type;

    private String orgName;

    private String orgWebsite;

    private String orgImageUri;

    private ImmutableSet<String> tags;

    public AccountUpdateRequest(String accountId) {
        this.accountId = accountId;
    }

    public String getName() {
        return name;
    }

    public AccountUpdateRequest setName(String name) {
        this.name = name;
        return this;
    }

    public Account.Status getStatus() {
        return status;
    }

    public AccountUpdateRequest setStatus(Account.Status status) {
        this.status = status;
        return this;
    }

    public Account.Type getType() {
        return type;
    }

    public AccountUpdateRequest setType(Account.Type type) {
        this.type = type;
        return this;
    }

    public String getOrgName() {
        return orgName;
    }

    public AccountUpdateRequest setOrgName(String orgName) {
        this.orgName = orgName;
        return this;
    }

    public String getOrgWebsite() {
        return orgWebsite;
    }

    public AccountUpdateRequest setOrgWebsite(String orgWebsite) {
        this.orgWebsite = orgWebsite;
        return this;
    }

    public String getOrgImageUri() {
        return orgImageUri;
    }

    public AccountUpdateRequest setOrgImageUri(String orgImageUri) {
        this.orgImageUri = orgImageUri;
        return this;
    }

    public ImmutableSet<String> getTags() {
        return tags;
    }

    public AccountUpdateRequest setTags(ImmutableSet<String> tags) {
        this.tags = tags;
        return this;
    }

    @Override
    protected Request request() {
        final String path = "accounts/" + accountId;
        return Request.put(path, this);
    }

    @Override
    protected Account parseEntity(Response response) {
        return response.readEntity(Account.class);
    }
}

