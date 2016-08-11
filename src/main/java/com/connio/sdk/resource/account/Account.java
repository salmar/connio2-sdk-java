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

package com.connio.sdk.resource.account;

import com.connio.sdk.request.account.AccountCreateRequest;
import com.connio.sdk.request.account.AccountDeleteRequest;
import com.connio.sdk.request.account.AccountFetchRequest;
import com.connio.sdk.request.account.AccountUpdateRequest;
import com.connio.sdk.resource.Resource;
import com.fasterxml.jackson.annotation.*;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableSet;
import org.apache.commons.lang3.text.WordUtils;
import org.joda.time.DateTime;

import java.util.Objects;
import java.util.Optional;

/**
 * Account resource
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Account extends Resource<AccountUpdateRequest, AccountDeleteRequest> {

    public enum Status {
        Created,
        Suspended,
        Active,
        Closed;

        @JsonValue
        public String value() {
            return name().toLowerCase();
        }

        @JsonCreator
        public static Status fromValue(@JsonProperty("name") final String name) {
            return Status.valueOf(WordUtils.capitalize(name.toLowerCase()));
        }
    }

    public enum Type {
        Full,
        Trial;

        @JsonValue
        public String value() {
            return name().toLowerCase();
        }

        @JsonCreator
        public static Status fromValue(@JsonProperty("name") final String name) {
            return Status.valueOf(WordUtils.capitalize(name.toLowerCase()));
        }
    }

    public enum Plan {
        Trial,
        Maker,
        Si,
        Enterprise;

        @JsonValue
        public String value() {
            return name().toLowerCase();
        }

        @JsonCreator
        public static Plan fromValue(@JsonProperty("name") final String name) {
            return Plan.valueOf(WordUtils.capitalize(name.toLowerCase()));
        }
    }

    private final String id;

    private final String name;

    private final Status status;

    private final Type type;

    private final String ownerId;

    private final String defaultAppProfId;

    private final String defaultAppId;

    private final String orgName;

    private final String orgWebsite;

    private final String orgImageUri;

    private final ImmutableSet<String> tags;

    private final DateTime dateCreated;

    private final DateTime dateModified;


    @JsonCreator
    public Account(@JsonProperty("id") final String id,
                   @JsonProperty("name") final String name,
                   @JsonProperty("ownerId") final String ownerId,
                   @JsonProperty("defaultAppProfId") final String defaultAppProfId,
                   @JsonProperty("defaultAppId") final String defaultAppId,
                   @JsonProperty("status") final Status status,
                   @JsonProperty("type") final Type type,
                   @JsonProperty("orgName") String orgName,
                   @JsonProperty("orgWebsite") String orgWebsite,
                   @JsonProperty("orgImageUri") String orgImageUri,
                   @JsonProperty("tags") ImmutableSet<String> tags,
                   @JsonProperty("dateCreated") final DateTime dateCreated,
                   @JsonProperty("dateModified") final DateTime dateModified) {

        this.id = id;
        this.name = name;
        this.ownerId = ownerId;
        this.defaultAppProfId = defaultAppProfId;
        this.defaultAppId = defaultAppId;
        this.status = status;
        this.type = type;
        this.orgName = orgName;
        this.orgWebsite = orgWebsite;
        this.orgImageUri = orgImageUri;
        this.tags = tags != null ? tags : ImmutableSet.<String>of();
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Status getStatus() {
        return status;
    }

    public Type getType() {
        return type;
    }

    public Optional<String> getOwnerId() {
        return Optional.ofNullable(ownerId);
    }

    public String getDefaultAppProfId() {
        return defaultAppProfId;
    }

    public String getDefaultAppId() {
        return defaultAppId;
    }

    public Optional<String> getOrgName() {
        return Optional.ofNullable(orgName);
    }

    public Optional<String> getOrgWebsite() {
        return Optional.ofNullable(orgWebsite);
    }

    public Optional<String> getOrgImageUri() {
        return Optional.ofNullable(orgImageUri);
    }

    public ImmutableSet<String> getTags() {
        return tags;
    }

    public DateTime getDateCreated() {
        return dateCreated;
    }

    public Optional<DateTime> getDateModified() {
        return Optional.ofNullable(dateModified);
    }

    /**
     * Creates an AccountFetchRequest to fetch an account given its id.
     * @param accountId of the account that wants to be fetched.
     * @return AccountFetchRequest to be executed.
     */
    public static AccountFetchRequest fetch(String accountId) {
        return new AccountFetchRequest(accountId);
    }

    /**
     * Creates and initialises a AccountCreateRequest with minimum data to create a new account. Note that the returning
     * AccountCreateRequest can be completed with all desired information that the request permits before executing it.
     * @param name account name
     * @param type account type
     * @return AccountCreateRequest be executed or completed with more data that the request permits before executing it.
     */
    public static AccountCreateRequest create(String name, Account.Type type) {
        return new AccountCreateRequest(name, type);
    }

    /**
     * Return a AccountUpdateRequest for the current account to be completed with the data that wants to be updated
     * before executing the request.
     * @return AccountUpdateRequest
     */
    @Override
    public AccountUpdateRequest update() {
        return new AccountUpdateRequest(this);
    }

    /**
     * Return a AccountDeleteRequest for the current account in order to delete it when executing.
     * @return AccountDeleteRequest
     */
    @Override
    public AccountDeleteRequest delete() {
        return new AccountDeleteRequest(this);
    }
    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        Account account = (Account) obj;
        return Objects.equals(this.getId(), account.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getOwnerId());
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper("Account")
                .add("id", id.toString())
                .add("name", name.toString())
                .add("ownerId", ownerId)
                .add("defaultAppProfId", defaultAppProfId)
                .add("defaultAppId", defaultAppId)
                .add("status", status)
                .add("type", type)
                .add("orgName", orgName)
                .add("orgWebsite", orgWebsite)
                .add("orgImageUri", orgImageUri)
                .add("tags", tags)
                .add("dateCreated", dateCreated)
                .add("dateModified", dateModified)
                .toString();
    }

}
