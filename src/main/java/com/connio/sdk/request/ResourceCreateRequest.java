package com.connio.sdk.request;

import com.connio.sdk.resource.Resource;

/**
 * Base abstract class for resource creation requests.
 * @param <T>
 */
public abstract class ResourceCreateRequest<T extends Resource> extends ApiRequest<T> {
}