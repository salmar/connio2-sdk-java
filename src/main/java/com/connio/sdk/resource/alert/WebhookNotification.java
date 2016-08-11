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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WebhookNotification that = (WebhookNotification) o;
        return Objects.equals(getMethod(), that.getMethod()) &&
                Objects.equals(getUrl(), that.getUrl()) &&
                Objects.equals(getSignatureKey(), that.getSignatureKey());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMethod(), getUrl(), getSignatureKey());
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
