package com.connio.sdk.request;

import com.connio.sdk.resource.Resource;

/**
 * Base abstract class for resource fetch requests.
 * @param <T>
 */
public abstract class ResourceFetchRequest<T extends Resource> extends ApiFetchRequest<T> {
}
