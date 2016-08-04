package com.connio.sdk.exception;

public abstract class ConnioException extends RuntimeException {

    public ConnioException(final String message) {
        this(message, null);
    }

    public ConnioException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
