package com.connio.sdk.resource.alert;

import com.connio.sdk.auth.Credentials;
import com.fasterxml.jackson.annotation.*;
import com.google.common.base.MoreObjects;
import org.apache.commons.lang3.text.WordUtils;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class MQTTNotification extends Notification {

    public enum Method {
        Post,
        Put,
        Get;

        @JsonValue
        public String value() {
            return name().toLowerCase();
        }

        @JsonCreator
        public static Method fromValue(@JsonProperty("name") final String name) {
            return Method.valueOf(WordUtils.capitalize(name.toLowerCase()));
        }
    }

    private final String url;

    private final String topic;

    private final Credentials credentials;

    @JsonCreator
    public MQTTNotification(@JsonProperty("name") String name,
                            @JsonProperty("message") String message,
                            @JsonProperty("url") String url,
                            @JsonProperty("topic") String topic,
                            @JsonProperty("credentials") Credentials credentials) {

        super(name, message);
        this.url = url;
        this.topic = topic;
        this.credentials = credentials;
    }

    public String getUrl() {
        return url;
    }

    public String getTopic() {
        return topic;
    }

    public Credentials getCredentials() {
        return credentials;
    }

    @Override
    public Action getAction() {
        return Action.Mqtt;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        MQTTNotification other = (MQTTNotification) obj;
        return  Objects.equals(this.name, other.name) &&
                Objects.equals(this.getAction(), other.getAction()) &&
                Objects.equals(this.message, other.message) &&
                Objects.equals(this.url, other.url) &&
                Objects.equals(this.topic, other.topic) &&
                Objects.equals(this.credentials, other.credentials);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, getAction(), message, url, topic, credentials);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper("WebhookNotification")
                .add("name", name)
                .add("action", getAction())
                .add("message", message)
                .add("url", url)
                .add("topic", topic)
                .add("credentials", credentials)
                .toString();
    }
}
