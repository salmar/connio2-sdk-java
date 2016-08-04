package com.connio.sdk.resource.alert;

import com.connio.sdk.resource.Resource;
import com.fasterxml.jackson.annotation.*;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import org.apache.commons.lang3.text.WordUtils;
import org.joda.time.DateTime;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Alert extends Resource {

    public enum Status {
        On,
        Off;

        @JsonValue
        public String value() {
            return name().toLowerCase();
        }
        @JsonCreator
        public static Status fromValue(@JsonProperty("name") final String name) {
            return Status.valueOf(WordUtils.capitalize(name.toLowerCase()));
        }
    }

    public enum Severity {
        Critical,
        Warning;

        @JsonValue
        public String value() {
            return name().toLowerCase();
        }

        @JsonCreator
        public static Status fromValue(@JsonProperty("name") final String name) {
            return Status.valueOf(WordUtils.capitalize(name.toLowerCase()));
        }
    }

    private final String id;

    private final String name;

    private final String accountId;

    private final String triggerPropId;

    private final String description;

    private final String ownerId;

    private final ImmutableSet<String> tags;

    private final Status status;

    private final String metric;

    private final ImmutableList<AlertCheck> checks;

    private final AlertHandler recover;

    private final ImmutableList<Notification> notifications;

    private final DateTime dateCreated;

    private final DateTime dateModified;


    public Alert(@JsonProperty("id") final String id,
                 @JsonProperty("name") final String name,
                 @JsonProperty("accountId") final String accountId,
                 @JsonProperty("triggerPropId") final String triggerPropId,
                 @JsonProperty("ownerId") final String ownerId,
                 @JsonProperty("description") final String description,
                 @JsonProperty("tags") final ImmutableSet<String> tags,
                 @JsonProperty("status") final Status status,
                 @JsonProperty("metric") final String metric,
                 @JsonProperty("checks") final ImmutableList<AlertCheck> checks,
                 @JsonProperty("recover") final AlertHandler recover,
                 @JsonProperty("notifications") final ImmutableList<Notification> notifications,
                 @JsonProperty("dateCreated") final DateTime dateCreated,
                 @JsonProperty("dateModified") final DateTime dateModified) {

        this.id = id;
        this.name = name;
        this.accountId = accountId;
        this.triggerPropId = triggerPropId;
        this.description = description;
        this.ownerId = ownerId;
        this.tags = tags;
        this.status = status;
        this.metric = metric;
        this.checks = checks;
        this.notifications = notifications;
        this.recover = recover;
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

    public String getTriggerPropId() {
        return triggerPropId;
    }

    public String getDescription() {
        return description;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public ImmutableSet<String> getTags() {
        return tags;
    }

    public Status getStatus() {
        return status;
    }

    public String getMetric() {
        return metric;
    }

    public ImmutableList<AlertCheck> getChecks() {
        return checks;
    }

    public AlertHandler getRecover() {
        return recover;
    }

    public ImmutableList<Notification> getNotifications() {
        return notifications;
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
        Alert other = (Alert) obj;
        return  Objects.equals(this.getId(), other.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper("Alert")
                .add("id", getId().toString())
                .add("name", getName())
                .add("accountId", getAccountId())
                .add("triggerPropId", getTriggerPropId())
                .toString();
    }

}

