package com.connio.sdk.request.alert;

import com.connio.sdk.request.ResourceFetchRequest;
import com.connio.sdk.http.Request;
import com.connio.sdk.http.Response;
import com.connio.sdk.resource.alert.Alert;
import com.connio.sdk.resource.device.Device;
import com.connio.sdk.resource.deviceprofile.DeviceProfile;

public class AlertFetchRequest extends ResourceFetchRequest<Alert> {

    private final String ownerId;

    private final String alertId;

    public AlertFetchRequest(DeviceProfile deviceProfile, String alertId) {
        this.ownerId = deviceProfile.getId();
        this.alertId = alertId;
    }

    public AlertFetchRequest(Device device, String alertId) {
        this.ownerId = device.getId();
        this.alertId = alertId;
    }

    @Override
    protected Request request() {
        final String ownerPath = (ownerId.startsWith("_dpf_")) ? "deviceprofiles" : "devices";
        final String path = ownerPath + "/" + ownerId + "/alerts/" + alertId;

        return Request.get(path);
    }

    @Override
    protected Alert parseResourceEntity(Response response) {
        return response.readEntity(Alert.class);
    }
}