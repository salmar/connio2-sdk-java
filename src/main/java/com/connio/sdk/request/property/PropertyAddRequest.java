package com.connio.sdk.request.property;

import com.connio.sdk.request.ResourceAddRequest;
import com.connio.sdk.http.Request;
import com.connio.sdk.http.Response;
import com.connio.sdk.resource.deviceprofile.DeviceProfile;
import com.connio.sdk.resource.property.Boundaries;
import com.connio.sdk.resource.property.Measurement;
import com.connio.sdk.resource.property.Property;
import com.connio.sdk.resource.property.Retention;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PropertyAddRequest extends ResourceAddRequest<Property> {

    private String name;

    private Property.Type type;

    private String deviceProfileId;

    private Property.Access access;

    private Property.PublishPolicy publish;

    private Measurement measurement;

    private Boundaries boundaries;

    private Retention retention;

    public PropertyAddRequest(DeviceProfile deviceProfile, String name, final Property.Type type) {
        this.deviceProfileId = deviceProfile.getId();
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public PropertyAddRequest setName(String name) {
        this.name = name;
        return this;
    }

    public Property.Type getType() {
        return type;
    }

    public PropertyAddRequest setType(Property.Type type) {
        this.type = type;
        return this;
    }

    public Property.Access getAccess() {
        return access;
    }

    public PropertyAddRequest setAccess(Property.Access access) {
        this.access = access;
        return this;
    }

    public Property.PublishPolicy getPublish() {
        return publish;
    }

    public PropertyAddRequest setPublish(Property.PublishPolicy publish) {
        this.publish = publish;
        return this;
    }

    public Measurement getMeasurement() {
        return measurement;
    }

    public PropertyAddRequest setMeasurement(Measurement measurement) {
        this.measurement = measurement;
        return this;
    }

    public Boundaries getBoundaries() {
        return boundaries;
    }

    public PropertyAddRequest setBoundaries(Boundaries boundaries) {
        this.boundaries = boundaries;
        return this;
    }

    public Retention getRetention() {
        return retention;
    }

    public PropertyAddRequest setRetention(Retention retention) {
        this.retention = retention;
        return this;
    }

    @Override
    protected Request request() {
        String path = "deviceprofiles/" + deviceProfileId + "/properties";
        return Request.post(path, this);
    }

    @Override
    protected Property parseEntity(Response response) {
        return response.readEntity(Property.class);
    }
}
