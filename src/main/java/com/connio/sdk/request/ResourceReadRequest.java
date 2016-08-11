package com.connio.sdk.request;

import com.connio.sdk.resource.Resource;

import java.util.List;

/**
 * Base abstract class for resource read requests.
 * @param <T>
 */
public abstract class ResourceReadRequest<T extends Resource> extends ApiRequest<List<T>> {
}