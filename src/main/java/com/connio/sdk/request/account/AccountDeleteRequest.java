package com.connio.sdk.request.account;

import com.connio.sdk.request.ResourceDeleteRequest;
import com.connio.sdk.http.Request;

public class AccountDeleteRequest extends ResourceDeleteRequest {

    private final String id;

    public AccountDeleteRequest(String id) {
        this.id = id;
    }

    @Override
    protected Request request() {
        final String path = "accounts/" + id;
        return Request.delete(path);
    }
}
