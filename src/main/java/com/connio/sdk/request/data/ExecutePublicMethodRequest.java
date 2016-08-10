package com.connio.sdk.request.data;

import com.connio.sdk.http.Request;
import com.connio.sdk.http.Response;
import com.connio.sdk.request.ApiRequest;
import com.connio.sdk.resource.data.MethodResult;
import com.connio.sdk.resource.device.Device;
import com.connio.sdk.resource.method.Method;

public class ExecutePublicMethodRequest extends ApiRequest<Object> {

    private final String deviceId;

    private final String methodId;

    private Object parameter;

    public ExecutePublicMethodRequest(Device device, Method method) {
        this.deviceId = device.getId();
        this.methodId = method.getId();
    }

    public Object getParameter() {
        return parameter;
    }

    public ExecutePublicMethodRequest setParameter(Object parameter) {
        this.parameter = parameter;
        return this;
    }

    @Override
    protected Request request() {
        final String path = "data/devices/" + deviceId + "/methods/" + methodId;

        return Request.put(path, parameter);
    }

    @Override
    protected Object parseEntity(Response response) {
        return response.readEntity(MethodResult.class).getResult();
    }
}