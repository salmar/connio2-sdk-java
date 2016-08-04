package com.connio.sdk.request.apiclient;

import com.connio.sdk.request.ResourceUpdateRequest;
import com.connio.sdk.auth.ApiKeyContext;
import com.connio.sdk.auth.ApiKeyScope;
import com.connio.sdk.http.Request;
import com.connio.sdk.http.Response;
import com.connio.sdk.resource.apiclient.ApiClient;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.ImmutableSet;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiClientAddRequest extends ResourceUpdateRequest<ApiClient> {

    private String description;

    private ImmutableSet<String> tags;

    private final ApiKeyContext context;

    private final ImmutableSet<ApiKeyScope> scope;

    private String rateLimit;


    public ApiClientAddRequest(@JsonProperty("context") ApiKeyContext context,
                               @JsonProperty("scope") ImmutableSet<ApiKeyScope> scope) {
        this.context = context;
        this.scope = scope;
    }

    public String getDescription() {
        return description;
    }

    public ApiClientAddRequest setDescription(String description) {
        this.description = description;
        return this;
    }

    public ImmutableSet<String> getTags() {
        return tags;
    }

    public ApiClientAddRequest setTags(ImmutableSet<String> tags) {
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

    public ApiClientAddRequest setRateLimit(String rateLimit) {
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
}
