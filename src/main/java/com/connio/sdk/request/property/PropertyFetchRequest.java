package com.connio.sdk.request.property;

import com.connio.sdk.request.ResourceFetchRequest;
import com.connio.sdk.http.Request;
import com.connio.sdk.http.Response;
import com.connio.sdk.resource.deviceprofile.DeviceProfile;
import com.connio.sdk.resource.property.Property;

public class PropertyFetchRequest extends ResourceFetchRequest<Property> {

    private final String deviceProfileId;

    private final String propertyId;

    public PropertyFetchRequest(DeviceProfile deviceProfile, String propertyId) {
        this.deviceProfileId = deviceProfile.getId();
        this.propertyId = propertyId;
    }

    @Override
    protected Request request() {
        final String path = "deviceprofiles/" + deviceProfileId + "/properties/" + propertyId;
        return Request.get(path);
    }

    @Override
    protected Property parseResourceEntity(Response response) {
        return response.readEntity(Property.class);
    }
}