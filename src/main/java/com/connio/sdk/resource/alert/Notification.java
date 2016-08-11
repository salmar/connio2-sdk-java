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
import org.apache.commons.lang3.text.WordUtils;


@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "action")
@JsonSubTypes({
        @JsonSubTypes.Type(value = WebhookNotification.class, name = "webhook"),
        @JsonSubTypes.Type(value = LogNotification.class, name = "log"),
        @JsonSubTypes.Type(value = MQTTNotification.class, name = "mqtt")
})
public abstract class Notification {

    public enum Action {
        Webhook,
        Log,
        Mqtt;

        @JsonValue
        public String value() {
            return name().toLowerCase();
        }

        @JsonCreator
        public static Action fromValue(@JsonProperty("name") final String name) {
            return Action.valueOf(WordUtils.capitalize(name.toLowerCase()));
        }
    }

    protected final String name;

    protected final String message;

    public Notification(String name, String message) {
        this.name = name;
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }

    public abstract Action getAction();
}
