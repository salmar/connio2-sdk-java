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
