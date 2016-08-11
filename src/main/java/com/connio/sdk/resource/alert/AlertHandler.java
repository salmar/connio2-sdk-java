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
import com.google.common.base.MoreObjects;

import java.util.Objects;

public class AlertHandler {

    private final String key;

    private final String notification;

    private final String next;

    private final String timeout;


    @JsonCreator
    public AlertHandler(@JsonProperty("key") String key,
                        @JsonProperty("notification") String notification,
                        @JsonProperty("next") String next,
                        @JsonProperty("timeout") String timeout) {

        this.key = key;
        this.notification = notification;
        this.next = next;
        this.timeout = timeout;
    }

    public String getKey() {
        return key;
    }

    public String getNotification() {
        return notification;
    }

    public String getNext() {
        return next;
    }

    public String getTimeout() {
        return timeout;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AlertHandler that = (AlertHandler) o;
        return Objects.equals(getKey(), that.getKey()) &&
                Objects.equals(getNotification(), that.getNotification()) &&
                Objects.equals(getNext(), that.getNext()) &&
                Objects.equals(getTimeout(), that.getTimeout());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getKey(), getNotification(), getNext(), getTimeout());
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper("AlertHandler")
                .add("key", getKey())
                .add("notification", getNotification())
                .add("next", getNext())
                .add("timeout", getTimeout())
                .toString();
    }
}
