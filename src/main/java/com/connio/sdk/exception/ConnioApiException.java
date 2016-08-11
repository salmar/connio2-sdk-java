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
