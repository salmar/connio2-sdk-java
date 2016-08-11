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

package com.connio.sdk.exception;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * General API exception returned from the server with specific information about the error cause.
 */
public class ConnioApiException extends ConnioException {

    private final Integer errorCode;

    private final String type;

    private final String message;

    private final String friendlyMessage;

    private final String moreInfoUrl;


    @JsonCreator
    public ConnioApiException(@JsonProperty("errorCode") Integer errorCode,
                              @JsonProperty("type") String type,
                              @JsonProperty("message") String message,
                              @JsonProperty("friendlyMessage") String friendlyMessage,
                              @JsonProperty("moreInfoUrl") String moreInfoUrl) {
        super(message);
        this.errorCode = errorCode;
        this.type = type;
        this.message = message;
        this.friendlyMessage = friendlyMessage;
        this.moreInfoUrl = moreInfoUrl;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public String getType() {
        return type;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public String getFriendlyMessage() {
        return friendlyMessage;
    }

    public String getMoreInfoUrl() {
        return moreInfoUrl;
    }
}
