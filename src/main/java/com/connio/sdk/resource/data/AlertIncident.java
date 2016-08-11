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

package com.connio.sdk.resource.data;

import com.connio.sdk.resource.alert.Alert;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import org.joda.time.DateTime;

import java.util.Objects;

public class AlertIncident {

    private final Alert.Severity severity;

    private final DateTime startTime;

    @JsonCreator
    public AlertIncident(@JsonProperty("severity") Alert.Severity severity,
                         @JsonProperty("startTime") DateTime startTime) {

        this.severity = severity;
        this.startTime = startTime;
    }

    public Alert.Severity getSeverity() {
        return severity;
    }

    public DateTime getStartTime() {
        return startTime;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        AlertIncident other = (AlertIncident) obj;
        return Objects.equals(this.severity, other.severity) && Objects.equals(this.startTime, other.startTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(severity, startTime);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper("AlertIncident")
                .add("severity", severity)
                .add("startTime", startTime)
                .toString();
    }
}
