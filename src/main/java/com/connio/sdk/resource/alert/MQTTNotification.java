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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MQTTNotification that = (MQTTNotification) o;
        return Objects.equals(getUrl(), that.getUrl()) &&
                Objects.equals(getTopic(), that.getTopic()) &&
                Objects.equals(getCredentials(), that.getCredentials());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUrl(), getTopic(), getCredentials());
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
