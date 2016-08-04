package com.connio.sdk.request.deviceprofile;

import com.connio.sdk.request.ResourceDeleteRequest;
import com.connio.sdk.http.Request;
import com.connio.sdk.resource.deviceprofile.DeviceProfile;

public class DeviceProfileDeleteRequest extends ResourceDeleteRequest {

    private final String id;

    public DeviceProfileDeleteRequest(DeviceProfile deviceProfile) {
        this.id = deviceProfile.getId();
    }

    @Override
    protected Request request() {
        final String path = "deviceprofiles/" + id;
        return Request.delete(path);
    }
}
