package com.connio.sdk.request.device;

import com.connio.sdk.request.ResourceDeleteRequest;
import com.connio.sdk.http.Request;

/**
 * Device delete request used to delete devices.
 */
public class DeviceDeleteRequest extends ResourceDeleteRequest {

    private final String id;

    public DeviceDeleteRequest(String id) {
        this.id = id;
    }

    @Override
    protected Request request() {
        final String path = "devices/" + id;
        return Request.delete(path);
    }
}
