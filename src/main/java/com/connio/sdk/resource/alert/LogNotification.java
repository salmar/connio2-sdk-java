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

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.google.common.base.MoreObjects;
import org.apache.commons.lang3.text.WordUtils;

import java.util.Objects;

public class LogNotification extends Notification {

    public enum LogLevel {
        Info,
        Alert,
        Warning,
        Error,
        Fatal;

        @JsonValue
        public String value() {
            return name().toLowerCase();
        }

        @JsonCreator
        public static LogLevel fromValue(@JsonProperty("name") final String name) {
            return LogLevel.valueOf(WordUtils.capitalize(name.toLowerCase()));
        }
    }

    private final LogLevel logLevel;

    public LogNotification(String name, String message, LogLevel logLevel) {
        super(name, message);
        this.logLevel = logLevel;
    }

    public LogLevel getLogLevel() {
        return logLevel;
    }

    @Override
    public Action getAction() {
        return Action.Log;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LogNotification that = (LogNotification) o;
        return Objects.equals(getLogLevel(), that.getLogLevel());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLogLevel());
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper("LogNotification")
                .add("name", name)
                .add("action", getAction())
                .add("message", message)
                .add("logLevel", logLevel)
                .toString();
    }
}
