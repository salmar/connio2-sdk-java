package com.connio.sdk.resource.user;

import com.connio.sdk.request.user.UserDeleteRequest;
import com.connio.sdk.request.user.UserFetchRequest;
import com.connio.sdk.request.user.UserInviteRequest;
import com.connio.sdk.request.user.UserUpdateRequest;
import com.connio.sdk.resource.Resource;
import com.fasterxml.jackson.annotation.*;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import org.apache.commons.lang3.text.WordUtils;
import org.joda.time.DateTime;

import java.util.Objects;
import java.util.Optional;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User extends Resource<UserUpdateRequest, UserDeleteRequest> {

    public enum Role {
        Admin,
        User,
        Anonymous;

        @JsonValue
        public String value() {
            return name().toLowerCase();
        }
        @JsonCreator
        public static Role fromValue(final String name) {
            return Role.valueOf(WordUtils.capitalize(name.toLowerCase()));
        }
    }

    public enum Status {
        Invited,
        Confirmed;

        @JsonValue
        public String value() {
            return name().toLowerCase();
        }
        @JsonCreator
        public static Status fromValue(final String name) {
            return Status.valueOf(WordUtils.capitalize(name.toLowerCase()));
        }
    }

    private final String id;

    private final String email;

    private final String password;

    private final String salt;

    private final ImmutableSet<Role> roles;

    private final Status status;

    private final String fullName;

    private final String accountId;

    private final ImmutableMap<String, Object> prefs;

    private final String avatarUri;

    private final String timezone;

    private final String lang;

    private final DateTime dateCreated;

    private final DateTime dateModified;

    @JsonCreator
    public User(@JsonProperty("id") final String id,
                @JsonProperty("email") final String email,
                @JsonProperty("password") final String password,
                @JsonProperty("salt") final String salt,
                @JsonProperty("status") final Status status,
                @JsonProperty("roles") final ImmutableSet<Role> roles,
                @JsonProperty("fullName") final String fullName,
                @JsonProperty("accountId") final String accountId,
                @JsonProperty("prefs") final ImmutableMap<String, Object> prefs,
                @JsonProperty("avatarUri") final String avatarUri,
                @JsonProperty("timezone") final String timezone,
                @JsonProperty("lang") final String lang,
                @JsonProperty("dateCreated") final DateTime dateCreated,
                @JsonProperty("dateModified") final DateTime dateModified)  {

        this.id = id;
        this.email = email;
        this.password = password;
        this.salt = salt;
        this.roles = roles;
        this.status = status;
        this.fullName = fullName;
        this.accountId = accountId;
        this.prefs = prefs;
        this.avatarUri = avatarUri;
        this.timezone = timezone;
        this.lang = lang;
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getSalt() {
        return salt;
    }

    public ImmutableSet<Role> getRoles() {
        return roles;
    }

    public Status getStatus() {
        return status;
    }

    public String getFullName() {
        return fullName;
    }

    public String getAccountId() {
        return accountId;
    }

    public ImmutableMap<String, Object> getPrefs() {
        return prefs;
    }

    public Optional<String> getAvatarUri() {
        return Optional.ofNullable(avatarUri);
    }

    public Optional<String> getTimezone() {
        return Optional.ofNullable(timezone);
    }

    public Optional<String> getLang() {
        return Optional.ofNullable(lang);
    }

    public DateTime getDateCreated() {
        return dateCreated;
    }

    public Optional<DateTime> getDateModified() {
        return Optional.ofNullable(dateModified);
    }

    public static UserFetchRequest fetch(String userId) {
        return new UserFetchRequest(userId);
    }

    public static UserInviteRequest invite(String email, String fullName) {
        return new UserInviteRequest(email, fullName);
    }

    @Override
    public UserUpdateRequest update() {
        return new UserUpdateRequest(this);
    }

    @Override
    public UserDeleteRequest delete() {
        return new UserDeleteRequest(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        User other = (User) obj;
        return Objects.equals(this.email, other.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFullName(), getEmail(), getAccountId());
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper("User")
                .add("id", id)
                .add("email", email)
                .add("password", "*********")
                .add("roles", roles)
                .add("status", status)
                .add("fullName", fullName)
                .add("avatarUri", avatarUri)
                .add("timezone", timezone)
                .add("lang", lang)
                .add("account", accountId)
                .toString();
    }
}
