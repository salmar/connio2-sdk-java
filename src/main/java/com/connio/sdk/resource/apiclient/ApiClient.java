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

package com.connio.sdk.resource.apiclient;

import com.connio.sdk.auth.ApiKeyContext;
import com.connio.sdk.auth.ApiKeyScope;
import com.connio.sdk.request.apiclient.ApiClientCreateRequest;
import com.connio.sdk.request.apiclient.ApiClientDeleteRequest;
import com.connio.sdk.request.apiclient.ApiClientFetchRequest;
import com.connio.sdk.request.apiclient.ApiClientUpdateRequest;
import com.connio.sdk.resource.Resource;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableSet;
import org.joda.time.DateTime;

import java.util.Objects;
import java.util.Optional;

/**
 * Api client resource
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiClient extends Resource<ApiClientUpdateRequest, ApiClientDeleteRequest> {

    private final String id;

    private final String name;

    private final String accountId;

    private final String description;

    private final ImmutableSet<String> tags;

    private final DateTime dateCreated;

    private final DateTime dateModified;

    @JsonCreator
    public ApiClient(@JsonProperty("id") final String id,
                     @JsonProperty("name") final String name,
                     @JsonProperty("accountId") final String accountId,
                     @JsonProperty("description") final String description,
                     @JsonProperty("tags") final ImmutableSet<String> tags,
                     @JsonProperty("dateCreated") final DateTime dateCreated,
                     @JsonProperty("dateModified") final DateTime dateModified) {

        this.id = id;
        this.name = name;
        this.accountId = accountId;
        this.description = description;
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

    public String getAccountId() {
        return accountId;
    }

    public Optional<String> getDescription() {
        return Optional.ofNullable(description);
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

    public static ApiClientFetchRequest fetch(String apiClientId) {
        return new ApiClientFetchRequest(apiClientId);
    }

    /**
     * Creates and initialises a ApiClientCreateRequest with minimum data to create a new api client. Note that the
     * returning ApiClientCreateRequest can be completed with all desired information that the request permits before
     * executing it.
     * @param context
     * @param scopes
     * @return
     */
    public static ApiClientCreateRequest create(ApiKeyContext context, ImmutableSet<ApiKeyScope> scopes) {
        return new ApiClientCreateRequest(context, scopes);
    }

    /**
     * Return a ApiClientUpdateRequest for the current api client to be completed with the data that wants to be updated
     * before executing the request.
     * @return ApiClientUpdateRequest.
     */
    @Override
    public ApiClientUpdateRequest update() {
        return new ApiClientUpdateRequest(this);
    }

    /**
     * Return a ApiClientDeleteRequest for the current api client in order to delete it when executing.
     * @return
     */
    @Override
    public ApiClientDeleteRequest delete() {
        return new ApiClientDeleteRequest(this);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        ApiClient other = (ApiClient) obj;
        return  Objects.equals(this.getId(), other.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAccountId(), getName());
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper("ApiClient")
                .add("id", getId().toString())
                .add("name", getName().toString())
                .add("accountId", getAccountId())
                .add("description", getDescription())
                .add("tags", getTags())
                .toString();
    }
}
