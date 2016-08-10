package com.connio.sdk.request.property;

import com.connio.sdk.request.ResourceCreateRequest;
import com.connio.sdk.http.Request;
import com.connio.sdk.http.Response;
import com.connio.sdk.resource.deviceprofile.DeviceProfile;
import com.connio.sdk.resource.property.Boundaries;
import com.connio.sdk.resource.property.Measurement;
import com.connio.sdk.resource.property.Property;
import com.connio.sdk.resource.property.Retention;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PropertyCreateRequest extends ResourceCreateRequest<Property> {

    private final String name;

    private final Property.Type type;

    private String deviceProfileId;

    private Property.Access access;

    private Property.PublishPolicy publish;

    private Measurement measurement;

    private Boundaries boundaries;

    private Retention retention;

    public PropertyCreateRequest(DeviceProfile deviceProfile, String name, final Property.Type type) {
        this.deviceProfileId = deviceProfile.getId();
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public Property.Type getType() {
        return type;
    }

    public Property.Access getAccess() {
        return access;
    }

    public PropertyCreateRequest setAccess(Property.Access access) {
        this.access = access;
        return this;
    }

    public Property.PublishPolicy getPublish() {
        return publish;
    }

    public PropertyCreateRequest setPublish(Property.PublishPolicy publish) {
        this.publish = publish;
        return this;
    }

    public Measurement getMeasurement() {
        return measurement;
    }

    public PropertyCreateRequest setMeasurement(Measurement measurement) {
        this.measurement = measurement;
        return this;
    }

    public Boundaries getBoundaries() {
        return boundaries;
    }

    public PropertyCreateRequest setBoundaries(Boundaries boundaries) {
        this.boundaries = boundaries;
        return this;
    }

    public Retention getRetention() {
        return retention;
    }

    public PropertyCreateRequest setRetention(Retention retention) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PropertyCreateRequest that = (PropertyCreateRequest) o;
        return Objects.equals(getName(), that.getName()) &&
                Objects.equals(getType(), that.getType()) &&
                Objects.equals(deviceProfileId, that.deviceProfileId) &&
                Objects.equals(getAccess(), that.getAccess()) &&
                Objects.equals(getPublish(), that.getPublish()) &&
                Objects.equals(getMeasurement(), that.getMeasurement()) &&
                Objects.equals(getBoundaries(), that.getBoundaries()) &&
                Objects.equals(getRetention(), that.getRetention());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getType(), deviceProfileId, getAccess(), getPublish(), getMeasurement(),
                getBoundaries(), getRetention());
    }
}
