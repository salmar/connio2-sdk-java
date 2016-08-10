package com.connio.sdk.resource.alert;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import org.apache.commons.lang3.text.WordUtils;

import java.util.Objects;

public class AlertCheck {

    public enum Severity {
        Critical,
        Warning;

        @JsonValue
        public String value() {
            return name().toLowerCase();
        }

        @JsonCreator
        public static Severity fromValue(@JsonProperty("name") final String name) {
            return Severity.valueOf(WordUtils.capitalize(name.toLowerCase()));
        }
    }

    private final Severity severity;

    private final String expression;

    private final ImmutableList<AlertHandler> handlers;


    @JsonCreator
    public AlertCheck(@JsonProperty("severity") Severity severity,
                      @JsonProperty("expression") String expression,
                      @JsonProperty("handlers") ImmutableList<AlertHandler> handlers) {

        this.severity = severity;
        this.expression = expression;
        this.handlers = handlers;
    }

    public Severity getSeverity() {
        return severity;
    }

    public String getExpression() {
        return expression;
    }

    public ImmutableList<AlertHandler> getHandlers() {
        return handlers;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        AlertCheck other = (AlertCheck) obj;
        return  Objects.equals(this.getSeverity(), other.getSeverity()) &&
                Objects.equals(this.getExpression(), other.getExpression()) &&
                Objects.equals(this.getHandlers(), other.getHandlers());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSeverity(), getExpression(), getHandlers());
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper("AlertCheck")
                .add("severity", getSeverity())
                .add("expression", getExpression())
                .add("handlers", getHandlers())
                .toString();
    }
}
