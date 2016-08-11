package com.connio.sdk.request.user;

import com.connio.sdk.http.Request;
import com.connio.sdk.http.Response;
import com.connio.sdk.request.ResourceFetchRequest;
import com.connio.sdk.resource.user.User;

/**
 * User fetch request used to fetch a user.
 */
public class UserFetchRequest extends ResourceFetchRequest<User> {

    private final String id;

    public UserFetchRequest(String id) {
        this.id = id;
    }

    @Override
    protected Request request() {
        final String path = "users/" + id;
        return Request.get(path);
    }

    @Override
    protected User parseResourceEntity(Response response) {
        return response.readEntity(User.class);
    }
}
