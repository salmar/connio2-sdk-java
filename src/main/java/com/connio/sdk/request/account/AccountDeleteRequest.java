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
