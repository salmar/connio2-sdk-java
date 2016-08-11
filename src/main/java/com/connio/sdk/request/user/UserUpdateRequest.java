package com.connio.sdk.request.user;

import com.connio.sdk.request.ResourceUpdateRequest;
import com.connio.sdk.http.Request;
import com.connio.sdk.http.Response;
import com.connio.sdk.resource.user.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

import java.util.Objects;

/**
 * User update request used to update a user.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserUpdateRequest extends ResourceUpdateRequest<User> {

    private final User user;

    private String password;

    private ImmutableSet<User.Role> roles;

    private String fullName;

    private String avatarUri;

    private String timezone;

    private String lang;

    private ImmutableMap<String, Object> prefs;

    public UserUpdateRequest(User user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public UserUpdateRequest setPassword(String password) {
        this.password = password;
        return this;
    }

    public ImmutableSet<User.Role> getRoles() {
        return roles;
    }

    public UserUpdateRequest setRoles(ImmutableSet<User.Role> roles) {
        this.roles = roles;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public UserUpdateRequest setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getAvatarUri() {
        return avatarUri;
    }

    public UserUpdateRequest setAvatarUri(String avatarUri) {
        this.avatarUri = avatarUri;
        return this;
    }

    public String getTimezone() {
        return timezone;
    }

    public UserUpdateRequest setTimezone(String timezone) {
        this.timezone = timezone;
        return this;
    }

    public String getLang() {
        return lang;
    }

    public UserUpdateRequest setLang(String lang) {
        this.lang = lang;
        return this;
    }

    public ImmutableMap<String, Object> getPrefs() {
        return prefs;
    }

    public UserUpdateRequest setPrefs(ImmutableMap<String, Object> prefs) {
        this.prefs = prefs;
        return this;
    }

    @Override
    protected Request request() {
        final String path = "users/" + user.getId();
        return Request.put(path, this);
    }

    @Override
    protected User parseEntity(Response response) {
        return response.readEntity(User.class);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserUpdateRequest that = (UserUpdateRequest) o;
        return Objects.equals(user, that.user) &&
                Objects.equals(getPassword(), that.getPassword()) &&
                Objects.equals(getRoles(), that.getRoles()) &&
                Objects.equals(getFullName(), that.getFullName()) &&
                Objects.equals(getAvatarUri(), that.getAvatarUri()) &&
                Objects.equals(getTimezone(), that.getTimezone()) &&
                Objects.equals(getLang(), that.getLang()) &&
                Objects.equals(getPrefs(), that.getPrefs());
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, getPassword(), getRoles(), getFullName(), getAvatarUri(), getTimezone(), getLang(), getPrefs());
    }
}
