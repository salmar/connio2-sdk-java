package com.connio.sdk.request.apiclient;

import com.connio.sdk.request.ResourceCreateRequest;
import com.connio.sdk.auth.ApiKeyContext;
import com.connio.sdk.auth.ApiKeyScope;
import com.connio.sdk.http.Request;
import com.connio.sdk.http.Response;
import com.connio.sdk.resource.apiclient.ApiClient;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.ImmutableSet;

import java.util.Objects;

/**
 * Api client create request used to create new api clients.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiClientCreateRequest extends ResourceCreateRequest<ApiClient> {

    private String description;

    private ImmutableSet<String> tags;

    private final ApiKeyContext context;

    private final ImmutableSet<ApiKeyScope> scope;

    private String rateLimit;


    public ApiClientCreateRequest(@JsonProperty("context") ApiKeyContext context,
                                  @JsonProperty("scope") ImmutableSet<ApiKeyScope> scope) {
        this.context = context;
        this.scope = scope;
    }

    public String getDescription() {
        return description;
    }

    public ApiClientCreateRequest setDescription(String description) {
        this.description = description;
        return this;
    }

    public ImmutableSet<String> getTags() {
        return tags;
    }

    public ApiClientCreateRequest setTags(ImmutableSet<String> tags) {
        this.tags = tags;
        return this;
    }

    public ApiKeyContext getContext() {
        return context;
    }

    public ImmutableSet<ApiKeyScope> getScope() {
        return scope;
    }

    public String getRateLimit() {
        return rateLimit;
    }

    public ApiClientCreateRequest setRateLimit(String rateLimit) {
        this.rateLimit = rateLimit;
        return this;
    }

    @Override
    protected Request request() {
        return Request.post("apiclients", this);
    }

    @Override
    protected ApiClient parseEntity(Response response) {
        return response.readEntity(ApiClient.class);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApiClientCreateRequest that = (ApiClientCreateRequest) o;
        return Objects.equals(getDescription(), that.getDescription()) &&
                Objects.equals(getTags(), that.getTags()) &&
                Objects.equals(getContext(), that.getContext()) &&
                Objects.equals(getScope(), that.getScope()) &&
                Objects.equals(getRateLimit(), that.getRateLimit());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDescription(), getTags(), getContext(), getScope(), getRateLimit());
    }
}
