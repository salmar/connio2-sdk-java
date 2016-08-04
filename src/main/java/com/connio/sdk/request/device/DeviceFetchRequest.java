package com.connio.sdk.request.device;

import com.connio.sdk.request.ResourceFetchRequest;
import com.connio.sdk.http.Request;
import com.connio.sdk.http.Response;
import com.connio.sdk.resource.device.Device;
import org.apache.commons.lang3.StringUtils;

public class DeviceFetchRequest extends ResourceFetchRequest<Device> {

    private final String deviceId;

    public DeviceFetchRequest(String deviceId) {
        this.deviceId = deviceId;
    }

    public DeviceFetchRequest() {
        this.deviceId = "";
    }

    @Override
    protected Request request() {
        final String deviceIdentificator = (StringUtils.isNotBlank(deviceId) ? deviceId : "_this_");
        final String path = "devices/" + deviceIdentificator;
        return Request.get(path);
    }

    @Override
    protected Device parseResourceEntity(Response response) {
        return response.readEntity(Device.class);
    }
}
