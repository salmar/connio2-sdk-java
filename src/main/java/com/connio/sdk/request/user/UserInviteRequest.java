package com.connio.sdk.request.user;

import com.connio.sdk.request.ApiRequest;
import com.connio.sdk.request.ResourceAddRequest;
import com.connio.sdk.http.Request;
import com.connio.sdk.http.Response;
import com.connio.sdk.resource.user.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.common.collect.ImmutableSet;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserInviteRequest extends ApiRequest<String> {

    private final String email;

    private final String fullName;

    private ImmutableSet<User.Role> roles;

    private String timezone;

    private String lang;

    public UserInviteRequest(String email, String fullName) {
        this.email = email;
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getFullName() {
        return fullName;
    }

    public ImmutableSet<User.Role> getRoles() {
        return roles;
    }

    public UserInviteRequest setRoles(ImmutableSet<User.Role> roles) {
        this.roles = roles;
        return this;
    }

    public String getTimezone() {
        return timezone;
    }

    public UserInviteRequest setTimezone(String timezone) {
        this.timezone = timezone;
        return this;
    }

    public String getLang() {
        return lang;
    }

    public UserInviteRequest setLang(String lang) {
        this.lang = lang;
        return this;
    }

    @Override
    protected Request request() {
        return Request.post("users/invite", this);
    }

    @Override
    protected String parseEntity(Response response) {
        return response.readEntity(String.class);
    }
}
