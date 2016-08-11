package com.connio.sdk.request;

import com.connio.sdk.resource.Resource;

/**
 * Base abstract class for resource update requests.
 * @param <T>
 */
public abstract class ResourceUpdateRequest<T extends Resource> extends ApiRequest<T> {
}
