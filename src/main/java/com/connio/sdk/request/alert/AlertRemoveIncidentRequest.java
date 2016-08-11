package com.connio.sdk.request.alert;

import com.connio.sdk.http.Request;
import com.connio.sdk.http.Response;
import com.connio.sdk.request.ApiRequest;
import com.connio.sdk.resource.alert.Alert;
import com.connio.sdk.resource.device.Device;

/**
 * Alert incident remove account. It is used to acknowledge alert notification and stop with notification lifecycle.
 */
public class AlertRemoveIncidentRequest extends ApiRequest<Void> {

    private final Device device;

    private final Alert alert;

    public AlertRemoveIncidentRequest(Device device, Alert alert) {
        this.device = device;
        this.alert = alert;
    }

    public Device getDevice() {
        return device;
    }

    public Alert getAlert() {
        return alert;
    }

    @Override
    protected Request request() {
        final String path = "devices/" + device.getId() + "/alerts/" + alert.getId() + "/incident";
        return Request.delete(path);
    }

    @Override
    protected Void parseEntity(Response response) {
        return null;
    }
}
