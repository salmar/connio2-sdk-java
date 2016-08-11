package com.connio.sdk.request.apiclient;

import com.connio.sdk.request.ResourceUpdateRequest;
import com.connio.sdk.auth.ApiKeyContext;
import com.connio.sdk.auth.ApiKeyScope;
import com.connio.sdk.http.Request;
import com.connio.sdk.http.Response;
import com.connio.sdk.resource.apiclient.ApiClient;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.common.collect.ImmutableSet;

import java.util.Objects;

/**
 * Api client update request used to update an api client.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiClientUpdateRequest extends ResourceUpdateRequest<ApiClient> {

    private final ApiClient apiClient;

    private String description;

    private ImmutableSet<String> tags;

    private String rateLimit;

    ApiKeyContext context;

    ImmutableSet<ApiKeyScope> scope;

    public ApiClientUpdateRequest(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public String getDescription() {
        return description;
    }

    public ApiClientUpdateRequest setDescription(String description) {
        this.description = description;
        return this;
    }

    public ImmutableSet<String> getTags() {
        return tags;
    }

    public ApiClientUpdateRequest setTags(ImmutableSet<String> tags) {
        this.tags = tags;
        return this;
    }

    public String getRateLimit() {
        return rateLimit;
    }

    public ApiClientUpdateRequest setRateLimit(String rateLimit) {
        this.rateLimit = rateLimit;
        return this;
    }

    public ApiKeyContext getContext() {
        return context;
    }

    public ApiClientUpdateRequest setContext(ApiKeyContext context) {
        this.context = context;
        return this;
    }

    public ImmutableSet<ApiKeyScope> getScope() {
        return scope;
    }

    public ApiClientUpdateRequest setScope(ImmutableSet<ApiKeyScope> scope) {
        this.scope = scope;
        return this;
    }

    @Override
    protected Request request() {
        final String path = "apiclients/" + apiClient.getId();
        return Request.put(path, this);
    }

    @Override
    protected ApiClient parseEntity(Response response) {
        return response.readEntity(ApiClient.class);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApiClientUpdateRequest that = (ApiClientUpdateRequest) o;
        return Objects.equals(apiClient, that.apiClient) &&
                Objects.equals(getDescription(), that.getDescription()) &&
                Objects.equals(getTags(), that.getTags()) &&
                Objects.equals(getRateLimit(), that.getRateLimit()) &&
                Objects.equals(getContext(), that.getContext()) &&
                Objects.equals(getScope(), that.getScope());
    }

    @Override
    public int hashCode() {
        return Objects.hash(apiClient, getDescription(), getTags(), getRateLimit(), getContext(), getScope());
    }
}
