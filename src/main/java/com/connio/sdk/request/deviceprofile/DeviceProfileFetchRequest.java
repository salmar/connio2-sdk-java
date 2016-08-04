package com.connio.sdk.request.deviceprofile;

import com.connio.sdk.request.ResourceFetchRequest;
import com.connio.sdk.http.Request;
import com.connio.sdk.http.Response;
import com.connio.sdk.resource.deviceprofile.DeviceProfile;

public class DeviceProfileFetchRequest extends ResourceFetchRequest<DeviceProfile> {

    private final String id;

    public DeviceProfileFetchRequest(String id) {
        this.id = id;
    }

    @Override
    protected Request request() {
        final String path = "deviceprofiles/" + id;
        return Request.get(path);
    }

    @Override
    protected DeviceProfile parseResourceEntity(Response response) {
        return response.readEntity(DeviceProfile.class);
    }
}
