package com.connio.sdk.request;

import com.connio.sdk.resource.Resource;

import java.util.List;

public abstract class ResourceReadRequest<T extends Resource> extends ApiRequest<List<T>> {
}