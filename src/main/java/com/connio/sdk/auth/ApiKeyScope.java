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

package com.connio.sdk.auth;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ApiKeyScope {

    SUBACCOUNT_CREATE("subaccount:create"),
    SUBACCOUNT_READ  ("subaccount:read"),
    SUBACCOUNT_MODIFY("subaccount:modify"),
    SUBACCOUNT_DELETE("subaccount:delete"),
    USER_CREATE      ("user:create"),
    USER_READ        ("user:read"),
    USER_MODIFY      ("user:modify"),
    USER_DELETE      ("user:delete"),
    DP_CREATE        ("deviceprofile:create"),
    DP_READ          ("deviceprofile:read"),
    DP_MODIFY        ("deviceprofile:modify"),
    DP_DELETE        ("deviceprofile:delete"),
    DEV_CREATE       ("device:create"),
    DEV_READ         ("device:read"),
    DEV_MODIFY       ("device:modify"),
    DEV_DELETE       ("device:delete"),
    DEV_READDATA     ("device:read-data"),
    DEV_WRITEDATA    ("device:write-data"),
    DEV_EXECMETHD    ("device:execute-method"),
    APPPROF_CREATE   ("appprofile:create"),
    APPPROF_READ     ("appprofile:read"),
    APPPROF_MODIFY   ("appprofile:modify"),
    APPPROF_DELETE   ("appprofile:delete"),
    APP_CREATE       ("app:create"),
    APP_READ         ("app:read"),
    APP_MODIFY       ("app:modify"),
    APP_DELETE       ("app:delete"),
    APP_READDATA     ("app:read-data"),
    APP_WRITEDATA    ("app:write-data"),
    APP_EXECMETHD    ("app:execute-method"),
    APICLIENT_CREATE ("apiclient:create"),
    APICLIENT_READ   ("apiclient:read"),
    APICLIENT_MODIFY ("apiclient:modify"),
    APICLIENT_DELETE ("apiclient:delete"),
    VIEW_CREATE      ("view:create"),
    VIEW_READ        ("view:read"),
    VIEW_MODIFY      ("view:modify"),
    VIEW_DELETE      ("view:delete"),
    INDEX_CREATE     ("index:create"),
    INDEX_READ       ("index:read"),
    INDEX_MODIFY     ("index:modify"),
    INDEX_DELETE     ("index:delete");

    private final String representation;

    ApiKeyScope(String representation) {
        this.representation = representation;
    }

    @JsonValue
    public String value() {
        return representation.toLowerCase();
    }

    @JsonCreator
    public static ApiKeyScope fromValue(String representation) {
        for (ApiKeyScope scope : ApiKeyScope.values()) {
            if (scope.representation.equals(representation)) return scope;
        }

        throw new RuntimeException(representation + "is not a valid api key scope");
    }
}
