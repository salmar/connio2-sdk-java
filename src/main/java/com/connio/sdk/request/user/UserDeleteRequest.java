package com.connio.sdk.request.user;

import com.connio.sdk.http.Request;
import com.connio.sdk.request.ResourceDeleteRequest;

/**
 * User delete request used to delete users.
 */
public class UserDeleteRequest extends ResourceDeleteRequest {

    private final String id;

    public UserDeleteRequest(String id) {
        this.id = id;
    }

    @Override
    protected Request request() {
        final String path = "users/" + id;
        return Request.delete(path);
    }
}