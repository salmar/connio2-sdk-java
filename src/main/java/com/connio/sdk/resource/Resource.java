package com.connio.sdk.resource;

import com.connio.sdk.request.ResourceDeleteRequest;
import com.connio.sdk.request.ResourceUpdateRequest;

import java.io.Serializable;

/**
 * Base abstract class for all Rest resources
 * @param <UR> resource update request
 * @param <DR> resource delete request
 */
public abstract class Resource<UR extends ResourceUpdateRequest, DR extends ResourceDeleteRequest> implements Serializable {

    private static final long serialVersionUID = 1L;

    public abstract UR update();

    public abstract  DR delete();
}
