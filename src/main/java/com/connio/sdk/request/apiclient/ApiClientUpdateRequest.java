package com.connio.sdk.request.apiclient;

import com.connio.sdk.request.ResourceUpdateRequest;
import com.connio.sdk.auth.ApiKeyContext;
import com.connio.sdk.auth.ApiKeyScope;
import com.connio.sdk.http.Request;
import com.connio.sdk.http.Response;
import com.connio.sdk.resource.apiclient.ApiClient;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.common.collect.ImmutableSet;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiClientUpdateRequest extends ResourceUpdateRequest<ApiClient> {

    private final String apiClientId;

    private String description;

    private ImmutableSet<String> tags;

    private String rateLimit;

    ApiKeyContext context;

    ImmutableSet<ApiKeyScope> scope;

    public ApiClientUpdateRequest(String apiClientId) {
        this.apiClientId = apiClientId;
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
        final String path = "apiclients/" + apiClientId;
        return Request.put(path, this);
    }

    @Override
    protected ApiClient parseEntity(Response response) {
        return response.readEntity(ApiClient.class);
    }
}
