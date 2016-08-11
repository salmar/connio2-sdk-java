package com.connio.sdk.request.account;

import com.connio.sdk.http.Request;
import com.connio.sdk.http.Response;
import com.connio.sdk.request.ResourceFetchRequest;
import com.connio.sdk.resource.account.Account;

/**
 * Account fetch request used to fetch an account.
 */
public class AccountFetchRequest extends ResourceFetchRequest<Account> {

    private final String id;

    public AccountFetchRequest(String id) {
        this.id = id;
    }

    @Override
    protected Request request() {
        final String path = "accounts/" + id;
        return Request.get(path);
    }

    @Override
    protected Account parseResourceEntity(Response response) {
        return response.readEntity(Account.class);
    }
}