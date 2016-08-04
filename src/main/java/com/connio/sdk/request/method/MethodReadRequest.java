package com.connio.sdk.request.method;

import com.connio.sdk.http.Request;
import com.connio.sdk.http.Response;
import com.connio.sdk.request.ResourceReadRequest;
import com.connio.sdk.resource.alert.Alert;
import com.connio.sdk.resource.device.Device;
import com.connio.sdk.resource.deviceprofile.DeviceProfile;

import javax.ws.rs.core.GenericType;
import java.util.List;

public class MethodReadRequest extends ResourceReadRequest<Alert> {

    private final String ownerId;

    public MethodReadRequest(DeviceProfile deviceProfile) {
        this.ownerId = deviceProfile.getId();
    }

    public MethodReadRequest(Device device) {
        this.ownerId = device.getId();
    }

    @Override
    protected Request request() {
        final String ownerPath = ownerId.startsWith("_dpf_") ? "deviceprofiles" : "devices";
        final String path = ownerPath + "/" + ownerId +"/methods";
        return Request.get(path);
    }

    @Override
    protected List<Alert> parseEntity(Response response) {
        return response.readEntity(new GenericType<List<Alert>>(){});
    }
}