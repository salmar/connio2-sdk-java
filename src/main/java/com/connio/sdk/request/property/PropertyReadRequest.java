package com.connio.sdk.request.property;

import com.connio.sdk.http.Request;
import com.connio.sdk.http.Response;
import com.connio.sdk.request.ResourceReadRequest;
import com.connio.sdk.resource.alert.Alert;
import com.connio.sdk.resource.device.Device;
import com.connio.sdk.resource.deviceprofile.DeviceProfile;
import com.connio.sdk.resource.property.Property;
import org.apache.commons.lang3.StringUtils;

import javax.ws.rs.core.GenericType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PropertyReadRequest extends ResourceReadRequest<Property> {

    private final String ownerId;

    private String type;

    public PropertyReadRequest(DeviceProfile deviceProfile) {
        this.ownerId = deviceProfile.getId();
    }

    public String getType() {
        return type;
    }

    public PropertyReadRequest setType(String type) {
        this.type = type;
        return this;
    }

    @Override
    protected Request request() {
        final String path = "deviceprofiles/" + ownerId + "/properties";

        final Map<String, String> filter = new HashMap<>();
        if (StringUtils.isNotBlank(type)) filter.put("type", type);

        return Request.get(path, filter);
    }

    @Override
    protected List<Property> parseEntity(Response response) {
        return response.readEntity(new GenericType<List<Property>>(){});
    }
}