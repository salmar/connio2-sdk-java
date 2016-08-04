package com.connio.sdk.request.property;

import com.connio.sdk.request.ResourceDeleteRequest;
import com.connio.sdk.http.Request;
import com.connio.sdk.resource.deviceprofile.DeviceProfile;

public class PropertyDeleteRequest extends ResourceDeleteRequest {

    private final String ownerId;

    private final String alertId;

    public PropertyDeleteRequest(DeviceProfile deviceProfile, String alertId) {
        this.ownerId = deviceProfile.getId();
        this.alertId = alertId;
    }

    @Override
    protected Request request() {
        final String path = "deviceprofiles/" + ownerId + "/properties/" + alertId;
        return Request.delete(path);
    }
}
