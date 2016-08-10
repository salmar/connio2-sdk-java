package com.connio.sdk.request.deviceprofile;

import com.connio.sdk.http.Request;
import com.connio.sdk.http.Response;
import com.connio.sdk.request.ResourceUpdateRequest;
import com.connio.sdk.resource.deviceprofile.DeviceProfile;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.common.collect.ImmutableSet;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeviceProfileUpdateRequest extends ResourceUpdateRequest<DeviceProfile> {

    private final String deviceProfileId;

    private String name;

    private String description;

    private String deviceClass;

    private String vendorName;

    private String productName;

    private String imageUri;

    private ImmutableSet<String> tags;

    public DeviceProfileUpdateRequest(DeviceProfile deviceProfile) {
        this.deviceProfileId = deviceProfile.getId();
    }

    public String getName() {
        return name;
    }

    public DeviceProfileUpdateRequest setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public DeviceProfileUpdateRequest setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getDeviceClass() {
        return deviceClass;
    }

    public DeviceProfileUpdateRequest setDeviceClass(String deviceClass) {
        this.deviceClass = deviceClass;
        return this;
    }

    public String getVendorName() {
        return vendorName;
    }

    public DeviceProfileUpdateRequest setVendorName(String vendorName) {
        this.vendorName = vendorName;
        return this;
    }

    public String getProductName() {
        return productName;
    }

    public DeviceProfileUpdateRequest setProductName(String productName) {
        this.productName = productName;
        return this;
    }

    public String getImageUri() {
        return imageUri;
    }

    public DeviceProfileUpdateRequest setImageUri(String imageUri) {
        this.imageUri = imageUri;
        return this;
    }

    public ImmutableSet<String> getTags() {
        return tags;
    }

    public DeviceProfileUpdateRequest setTags(ImmutableSet<String> tags) {
        this.tags = tags;
        return this;
    }

    @Override
    protected Request request() {
        final String path = "deviceprofiles/" + deviceProfileId;
        return Request.put(path, this);
    }

    @Override
    protected DeviceProfile parseEntity(Response response) {
        return response.readEntity(DeviceProfile.class);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeviceProfileUpdateRequest that = (DeviceProfileUpdateRequest) o;
        return Objects.equals(deviceProfileId, that.deviceProfileId) &&
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(getDescription(), that.getDescription()) &&
                Objects.equals(getDeviceClass(), that.getDeviceClass()) &&
                Objects.equals(getVendorName(), that.getVendorName()) &&
                Objects.equals(getProductName(), that.getProductName()) &&
                Objects.equals(getImageUri(), that.getImageUri()) &&
                Objects.equals(getTags(), that.getTags());
    }

    @Override
    public int hashCode() {
        return Objects.hash(deviceProfileId, getName(), getDescription(), getDeviceClass(), getVendorName(), getProductName(), getImageUri(), getTags());
    }
}
