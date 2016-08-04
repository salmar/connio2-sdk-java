package com.connio.sdk.request.account;

import com.connio.sdk.request.ResourceAddRequest;
import com.connio.sdk.http.Request;
import com.connio.sdk.http.Response;
import com.connio.sdk.resource.account.Account;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountAddRequest extends ResourceAddRequest<Account> {

    private final String name;

    private final Account.Type type;

    private Account.Status status;

    private String orgName;

    private String orgWebsite;

    private String orgImageUri;


    public AccountAddRequest(String name, Account.Type type) {
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

    public AccountAddRequest setStatus(Account.Status status) {
        this.status = status;
        return this;
    }

    public String getOrgName() {
        return orgName;
    }

    public AccountAddRequest setOrgName(String orgName) {
        this.orgName = orgName;
        return this;
    }

    public String getOrgWebsite() {
        return orgWebsite;
    }

    public AccountAddRequest setOrgWebsite(String orgWebsite) {
        this.orgWebsite = orgWebsite;
        return this;
    }

    public String getOrgImageUri() {
        return orgImageUri;
    }

    public AccountAddRequest setOrgImageUri(String orgImageUri) {
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
}
