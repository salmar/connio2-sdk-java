package com.connio.sdk.exception;

/**
 * Authentication error exception. It is thrown when invalid credentials are provided.
 */
public class AuthenticationException extends ConnioException {

    public AuthenticationException(final String message) {
        super(message);
    }
}
