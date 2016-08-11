package com.connio.sdk;

import com.connio.sdk.exception.AuthenticationException;

import java.util.concurrent.ExecutorService;

/**
 * Pseudo singleton used to execute requests without having to pass a connio api client explicitly as they will retrieve
 * it from this class. Ideally it should be initialized during application bootstrap before executing any request.
 */
public class Connio {

    private static String username;
    private static String password;
    private static ExecutorService executorService;
    private static ConnioApiClient apiClient;

    private Connio() {}

    /**
     * Initializes the connio api client with given credentials.
     * @param username
     * @param password
     */
    public static synchronized void init(String username, String password) {
        Connio.setUsername(username);
        Connio.setPassword(password);
    }

    /**
     * Initializes the connio api client with given credentials and thread pool.
     * @param username
     * @param password
     * @param executorService
     */
    public static synchronized void init(String username, String password, ExecutorService executorService) {
        Connio.setUsername(username);
        Connio.setPassword(password);
        Connio.setExecutorService(executorService);
    }

    public static synchronized void setUsername(String username) {
        if (username == null) {
            throw new AuthenticationException("Username can not be null");
        }

        if (!username.equals(Connio.username)) {
            Connio.invalidate();
        }

        Connio.username = username;
    }

    public static synchronized void setPassword(String password) {
        if (password == null) {
            throw new AuthenticationException("Password can not be null");
        }

        if (!password.equals(Connio.password)) {
            Connio.invalidate();
        }

        Connio.password = password;
    }

    public static synchronized void setExecutorService(ExecutorService executorService) {
        if (executorService == null) {
            throw new AuthenticationException("ExecutorService can not be null");
        }

        Connio.invalidate();
        Connio.executorService = executorService;
    }

    public static synchronized ConnioApiClient getApiClient() {
        if (Connio.apiClient == null) {
            if (Connio.username == null || Connio.password == null) {
                throw new AuthenticationException(
                    "ConnioApiClient was used before credentials were set, please call Connio.init()"
                );
            }

            try {
                Connio.apiClient = Connio.executorService == null ? new DefaultConnioApiClient(Connio.username, Connio.password) :
                        new DefaultConnioApiClient(Connio.username, Connio.password, Connio.executorService);

            } catch (Exception e) {
                throw new RuntimeException("Error initialising ConnioApiClient", e);
            }
        }

        return Connio.apiClient;
    }

    public static synchronized void terminate() {
        invalidate();
    }

    private static synchronized void invalidate() {
        if (Connio.apiClient != null) ((DefaultConnioApiClient)Connio.apiClient).terminate();
        Connio.apiClient = null;
    }
}
