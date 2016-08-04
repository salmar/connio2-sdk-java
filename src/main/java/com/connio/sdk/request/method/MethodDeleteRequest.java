package com.connio.sdk.request.method;

import com.connio.sdk.request.ResourceDeleteRequest;
import com.connio.sdk.http.Request;
import com.connio.sdk.resource.deviceprofile.DeviceProfile;

public class MethodDeleteRequest extends ResourceDeleteRequest {

    private final String ownerId;

    private final String alertId;

    public MethodDeleteRequest(DeviceProfile deviceProfile, String alertId) {
        this.ownerId = deviceProfile.getId();
        this.alertId = alertId;
    }

    @Override
    protected Request request() {
        final String path = "deviceprofiles/" + ownerId + "/methods/" + alertId;
        return Request.delete(path);
    }
}
