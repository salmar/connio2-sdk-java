package com.connio.sdk.resource.apiclient;

import com.connio.sdk.auth.ApiKeyContext;
import com.connio.sdk.auth.ApiKeyScope;
import com.connio.sdk.request.account.AccountFetchRequest;
import com.connio.sdk.request.apiclient.ApiClientAddRequest;
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

    public static ApiClientAddRequest create(ApiKeyContext context, ImmutableSet<ApiKeyScope> scopes) {
        return new ApiClientAddRequest(context, scopes);
    }

    @Override
    public ApiClientUpdateRequest update() {
        return new ApiClientUpdateRequest(this);
    }

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
