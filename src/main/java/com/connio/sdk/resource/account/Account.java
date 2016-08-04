package com.connio.sdk.resource.account;

import com.connio.sdk.resource.Resource;
import com.fasterxml.jackson.annotation.*;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableSet;
import org.apache.commons.lang3.text.WordUtils;
import org.joda.time.DateTime;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Account extends Resource {

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
        this.tags = tags;
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

    public String getOwnerId() {
        return ownerId;
    }

    public String getDefaultAppProfId() {
        return defaultAppProfId;
    }

    public String getDefaultAppId() {
        return defaultAppId;
    }

    public String getOrgName() {
        return orgName;
    }

    public String getOrgWebsite() {
        return orgWebsite;
    }

    public String getOrgImageUri() {
        return orgImageUri;
    }

    public ImmutableSet<String> getTags() {
        return tags;
    }

    public DateTime getDateCreated() {
        return dateCreated;
    }

    public DateTime getDateModified() {
        return dateModified;
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
                .add("id", getId().toString())
                .add("name", getName().toString())
                .add("ownerId", getOwnerId())
                .add("defaultAppProfId", getDefaultAppProfId())
                .add("defaultAppId", getDefaultAppId())
                .add("status", getStatus())
                .add("type", getType())
                .add("orgName", getOrgName())
                .add("orgWebsite", getOrgWebsite())
                .add("orgImageUri", getOrgImageUri())
                .add("tags", getTags())
                .add("dateCreated", getDateCreated())
                .add("dateModified", getDateModified())
                .toString();
    }

}
