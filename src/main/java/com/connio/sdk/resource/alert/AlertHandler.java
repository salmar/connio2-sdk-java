package com.connio.sdk.resource.alert;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;

import java.util.Objects;

public class AlertHandler {

    private final String key;

    private final String notification;

    private final String next;

    private final String timeout;


    @JsonCreator
    public AlertHandler(@JsonProperty("key") String key,
                        @JsonProperty("notification") String notification,
                        @JsonProperty("next") String next,
                        @JsonProperty("timeout") String timeout) {

        this.key = key;
        this.notification = notification;
        this.next = next;
        this.timeout = timeout;
    }

    public String getKey() {
        return key;
    }

    public String getNotification() {
        return notification;
    }

    public String getNext() {
        return next;
    }

    public String getTimeout() {
        return timeout;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        AlertHandler other = (AlertHandler) obj;
        return  Objects.equals(this.key, other.key) &&
                Objects.equals(this.notification, other.notification) &&
                Objects.equals(this.next, other.next) &&
                Objects.equals(this.timeout, other.timeout);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getKey(), getNotification(), getNext(), getTimeout());
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper("AlertHandler")
                .add("key", getKey())
                .add("notification", getNotification())
                .add("next", getNext())
                .add("timeout", getTimeout())
                .toString();
    }
}
