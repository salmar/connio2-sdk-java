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

package com.connio.sdk.request.user;

import com.connio.sdk.request.ApiRequest;
import com.connio.sdk.http.Request;
import com.connio.sdk.http.Response;
import com.connio.sdk.resource.user.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.common.collect.ImmutableSet;

import java.util.Objects;

/**
 * User invite request used to invite / create a new user.
 */
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserInviteRequest that = (UserInviteRequest) o;
        return Objects.equals(getEmail(), that.getEmail()) &&
                Objects.equals(getFullName(), that.getFullName()) &&
                Objects.equals(getRoles(), that.getRoles()) &&
                Objects.equals(getTimezone(), that.getTimezone()) &&
                Objects.equals(getLang(), that.getLang());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmail(), getFullName(), getRoles(), getTimezone(), getLang());
    }
}
