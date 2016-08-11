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

package com.connio.sdk.auth;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import org.joda.time.DateTime;

import java.util.Objects;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiKey {

    private final String id;

    private final String secret;

    private final String ownerId;

    private final String accountId;

    private final ApiKeyContext context;

    private final Set<ApiKeyScope> scope;

    private final String rateLimit;

    private final DateTime dateCreated;

    private final DateTime dateModified;

    public ApiKey(@JsonProperty("id") final String id,
                  @JsonProperty("secret") final String secret,
                  @JsonProperty("ownerId") final String ownerId,
                  @JsonProperty("accountId") final String accountId,
                  @JsonProperty("context") final ApiKeyContext context,
                  @JsonProperty("scope") final Set<ApiKeyScope> scope,
                  @JsonProperty("rateLimit") final String rateLimit,
                  @JsonProperty("dateCreated") final DateTime dateCreated,
                  @JsonProperty("dateModified") final DateTime dateModified) {

        this.id = id;
        this.secret = secret;
        this.ownerId = ownerId;
        this.accountId = accountId;
        this.context = context;
        this.scope = scope;
        this.rateLimit = rateLimit;
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
    }

    public String getId() {
        return id;
    }

    public String getSecret() {
        return secret;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public String getAccountId() {
        return accountId;
    }

    public ApiKeyContext getContext() {
        return context;
    }

    public Set<ApiKeyScope> getScope() {
        return scope;
    }

    public String getRateLimit() {
        return rateLimit;
    }

    public DateTime getDateCreated() {
        return dateCreated;
    }

    public DateTime getDateModified() {
        return dateModified;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ApiKey that = (ApiKey) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper("ApiKey")
                .add("id", id)
                .add("secret", secret)
                .add("ownerId", ownerId)
                .toString();
    }
}
