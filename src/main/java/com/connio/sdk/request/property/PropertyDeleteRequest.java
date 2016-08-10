package com.connio.sdk.request.property;

import com.connio.sdk.request.ResourceDeleteRequest;
import com.connio.sdk.http.Request;
import com.connio.sdk.resource.deviceprofile.DeviceProfile;

public class PropertyDeleteRequest extends ResourceDeleteRequest {

    private final String ownerId;

    private final String propertyId;

    public PropertyDeleteRequest(String deviceProfileId, String propertyId) {
        this.ownerId = deviceProfileId;
        this.propertyId = propertyId;

        if (deviceProfileId == null || !deviceProfileId.startsWith("_dpf_"))
            throw new IllegalArgumentException("Invalid device profile id");
    }

    public PropertyDeleteRequest(DeviceProfile deviceProfile, String propertyId) {
        this.ownerId = deviceProfile.getId();
        this.propertyId = propertyId;
    }

    @Override
    protected Request request() {
        final String path = "deviceprofiles/" + ownerId + "/properties/" + propertyId;
        return Request.delete(path);
    }
}
