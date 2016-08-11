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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AlertCheck that = (AlertCheck) o;
        return Objects.equals(getSeverity(), that.getSeverity()) &&
                Objects.equals(getExpression(), that.getExpression()) &&
                Objects.equals(getHandlers(), that.getHandlers());
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
