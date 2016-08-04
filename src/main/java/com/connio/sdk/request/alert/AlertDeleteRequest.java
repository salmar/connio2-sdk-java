package com.connio.sdk.request.alert;

import com.connio.sdk.request.ResourceDeleteRequest;
import com.connio.sdk.http.Request;
import com.connio.sdk.resource.device.Device;
import com.connio.sdk.resource.deviceprofile.DeviceProfile;

public class AlertDeleteRequest extends ResourceDeleteRequest {

    private final String ownerId;

    private final String alertId;

    public AlertDeleteRequest(DeviceProfile deviceProfile, String alertId) {
        this.ownerId = deviceProfile.getId();
        this.alertId = alertId;
    }

    public AlertDeleteRequest(Device device, String alertId) {
        this.ownerId = device.getId();
        this.alertId = alertId;
    }

    @Override
    protected Request request() {
        final String ownerPath = ownerId.startsWith("_dpf_") ? "deviceprofiles" : "devices";
        final String path = ownerPath +"/" + ownerId + "/" + "alerts/" + alertId;
        return Request.delete(path);
    }
}
