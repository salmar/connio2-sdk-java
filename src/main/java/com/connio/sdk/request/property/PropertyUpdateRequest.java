package com.connio.sdk.request.property;

import com.connio.sdk.request.ResourceUpdateRequest;
import com.connio.sdk.http.Request;
import com.connio.sdk.http.Response;
import com.connio.sdk.resource.deviceprofile.DeviceProfile;
import com.connio.sdk.resource.property.Boundaries;
import com.connio.sdk.resource.property.Measurement;
import com.connio.sdk.resource.property.Property;
import com.connio.sdk.resource.property.Retention;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Objects;

/**
 * Property update request used to update a property.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PropertyUpdateRequest extends ResourceUpdateRequest<Property> {

    private final String deviceProfileId;

    private final String propertyId;

    private String name;

    private Property.Access access;

    private Property.PublishPolicy publish;

    private Measurement measurement;

    private Boundaries boundaries;

    private Retention retention;


    public PropertyUpdateRequest(String deviceProfileId, String propertyId) {
        this.deviceProfileId = deviceProfileId;
        this.propertyId = propertyId;

        if (deviceProfileId == null || !deviceProfileId.startsWith("_dpf_"))
            throw new IllegalArgumentException("Invalid device profile id");
    }

    public PropertyUpdateRequest(DeviceProfile deviceProfile, String propertyId) {
        this.deviceProfileId = deviceProfile.getId();
        this.propertyId = propertyId;
    }

    public String getName() {
        return name;
    }

    public PropertyUpdateRequest setName(String name) {
        this.name = name;
        return this;
    }

    public Property.Access getAccess() {
        return access;
    }

    public PropertyUpdateRequest setAccess(Property.Access access) {
        this.access = access;
        return this;
    }

    public Property.PublishPolicy getPublish() {
        return publish;
    }

    public PropertyUpdateRequest setPublish(Property.PublishPolicy publish) {
        this.publish = publish;
        return this;
    }

    public Measurement getMeasurement() {
        return measurement;
    }

    public PropertyUpdateRequest setMeasurement(Measurement measurement) {
        this.measurement = measurement;
        return this;
    }

    public Boundaries getBoundaries() {
        return boundaries;
    }

    public PropertyUpdateRequest setBoundaries(Boundaries boundaries) {
        this.boundaries = boundaries;
        return this;
    }

    public Retention getRetention() {
        return retention;
    }

    public PropertyUpdateRequest setRetention(Retention retention) {
        this.retention = retention;
        return this;
    }

    @Override
    protected Request request() {
        final String path = "deviceprofiles/" + deviceProfileId + "/properties/" + propertyId;
        return Request.put(path, this);
    }

    @Override
    protected Property parseEntity(Response response) {
        return response.readEntity(Property.class);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PropertyUpdateRequest that = (PropertyUpdateRequest) o;
        return Objects.equals(deviceProfileId, that.deviceProfileId) &&
                Objects.equals(propertyId, that.propertyId) &&
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(getAccess(), that.getAccess()) &&
                Objects.equals(getPublish(), that.getPublish()) &&
                Objects.equals(getMeasurement(), that.getMeasurement()) &&
                Objects.equals(getBoundaries(), that.getBoundaries()) &&
                Objects.equals(getRetention(), that.getRetention());
    }

    @Override
    public int hashCode() {
        return Objects.hash(deviceProfileId, propertyId, getName(), getAccess(), getPublish(), getMeasurement(),
                getBoundaries(), getRetention());
    }
}
