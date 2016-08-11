package com.connio.sdk.request.data;

import com.connio.sdk.http.Request;
import com.connio.sdk.http.Response;
import com.connio.sdk.request.ApiRequest;
import com.connio.sdk.response.MethodResult;
import com.connio.sdk.resource.device.Device;
import com.connio.sdk.resource.method.Method;

/**
 * Read public "getter" methods, that is, public methods that don't accept parameters and are connected to a property.
 */
public class ReadPublicMethodRequest extends ApiRequest<Object> {

    private final String deviceId;

    private final String methodId;

    public ReadPublicMethodRequest(Device device, Method method) {
        this.deviceId = device.getId();
        this.methodId = method.getId();
    }

    @Override
    protected Request request() {
        final String path = "data/devices/" + deviceId + "/methods/" + methodId;
        return Request.get(path);
    }

    @Override
    protected Object parseEntity(Response response) {
        return response.readEntity(MethodResult.class).getResult();
    }
}