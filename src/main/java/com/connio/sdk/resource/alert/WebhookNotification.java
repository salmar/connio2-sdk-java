package com.connio.sdk.resource.alert;

import com.fasterxml.jackson.annotation.*;
import com.google.common.base.MoreObjects;
import org.apache.commons.lang3.text.WordUtils;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class WebhookNotification extends Notification {

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

    private final Method method;

    private final String url;

    private final String signatureKey;

    @JsonCreator
    public WebhookNotification(@JsonProperty("name") String name,
                               @JsonProperty("message") String message,
                               @JsonProperty("method") Method method,
                               @JsonProperty("url") String url,
                               @JsonProperty("signatureKey") String signatureKey) {

        super(name, message);
        this.method = method;
        this.url = url;
        this.signatureKey = signatureKey;
    }

    public Method getMethod() {
        return method;
    }

    public String getUrl() {
        return url;
    }

    public String getSignatureKey() {
        return signatureKey;
    }

    @Override
    public Action getAction() {
        return Action.Webhook;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        WebhookNotification other = (WebhookNotification) obj;
        return  Objects.equals(this.name, other.name) &&
                Objects.equals(this.getAction(), other.getAction()) &&
                Objects.equals(this.message, other.message) &&
                Objects.equals(this.method, other.method) &&
                Objects.equals(this.url, other.url) &&
                Objects.equals(this.signatureKey, other.signatureKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, getAction(), message, method, url, signatureKey);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper("WebhookNotification")
                .add("name", name)
                .add("action", getAction())
                .add("message", message)
                .add("method", method)
                .add("url", url)
                .add("signatureKey", signatureKey)
                .toString();
    }
}
