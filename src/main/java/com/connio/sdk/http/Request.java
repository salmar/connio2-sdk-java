package com.connio.sdk.http;

import com.google.common.base.MoreObjects;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;

/**
 * HTTP Request. It contains all information needed to model different calls to the Connio public API.
 */
public class Request {

    private final RequestMethod method;

    private final String path;

    private final Map<String, String> queryParams;

    private final Object body;

    private Request(RequestMethod method, String path, Map<String, String> queryParams, Object body) {
        this.method = method;
        this.path = path;
        this.queryParams = queryParams;
        this.body = body;
    }

    /**
     * Creates GET request to the given path
     * @param path of the request
     * @return Request
     */
    public static Request get(String path) {
        return new Request(RequestMethod.GET, path, Collections.emptyMap(), null);
    }

    /**
     * Creates GET request to the given path with the specified query parameters
     * @param path of the request
     * @param queryParams of the request
     * @return Request
     */
    public static Request get(String path, Map<String, String> queryParams) {
        return new Request(RequestMethod.GET, path, queryParams, null);
    }

    /**
     * Creates POST request to the given path with the specified object payload.
     * @param path of the request
     * @param payload or body of the request
     * @return Request
     */
    public static Request post(String path, Object payload) {
        return new Request(RequestMethod.POST, path, Collections.emptyMap(), payload);
    }

    /**
     * Creates PUT request to the given path with the specified object payload
     * @param path of the request
     * @param payload or body of the request
     * @return Request
     */
    public static Request put(String path, Object payload) {
        return new Request(RequestMethod.PUT, path, Collections.emptyMap(), payload);
    }

    /**
     * Creates DELETE request to the given path
     * @param path of the request
     * @return Request
     */
    public static Request delete(String path) {
        return new Request(RequestMethod.DELETE, path, Collections.emptyMap(), null);
    }

    public RequestMethod getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }

    public Map<String, String> getQueryParams() {
        return queryParams;
    }

    public Object getBody() {
        return body;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return Objects.equals(getMethod(), request.getMethod()) &&
                Objects.equals(getPath(), request.getPath()) &&
                Objects.equals(getQueryParams(), request.getQueryParams()) &&
                Objects.equals(getBody(), request.getBody());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMethod(), getPath(), getQueryParams(), getBody());
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper("Request")
                .add("method", method)
                .add("path", path)
                .add("queryParams", queryParams)
                .add("body", body)
                .toString();
    }
}
