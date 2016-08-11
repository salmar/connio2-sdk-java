package com.connio.sdk.request.method;

import com.connio.sdk.request.ResourceDeleteRequest;
import com.connio.sdk.http.Request;
import com.connio.sdk.resource.deviceprofile.DeviceProfile;

/**
 * Method delete request used to delete methods.
 */
public class MethodDeleteRequest extends ResourceDeleteRequest {

    private final String ownerId;

    private final String methodId;

    public MethodDeleteRequest(String deviceProfileId, String methodId) {
        this.ownerId = deviceProfileId;
        this.methodId = methodId;

        if (deviceProfileId == null || !deviceProfileId.startsWith("_dpf_"))
            throw new IllegalArgumentException("Invalid device profile");
    }

    public MethodDeleteRequest(DeviceProfile deviceProfile, String methodId) {
        this.ownerId = deviceProfile.getId();
        this.methodId = methodId;
    }

    @Override
    protected Request request() {
        final String path = "deviceprofiles/" + ownerId + "/methods/" + methodId;
        return Request.delete(path);
    }
}
