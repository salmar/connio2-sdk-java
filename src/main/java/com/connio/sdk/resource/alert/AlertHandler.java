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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AlertHandler that = (AlertHandler) o;
        return Objects.equals(getKey(), that.getKey()) &&
                Objects.equals(getNotification(), that.getNotification()) &&
                Objects.equals(getNext(), that.getNext()) &&
                Objects.equals(getTimeout(), that.getTimeout());
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
