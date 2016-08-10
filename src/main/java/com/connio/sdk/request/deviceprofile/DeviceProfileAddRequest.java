package com.connio.sdk.request.deviceprofile;

import com.connio.sdk.request.ResourceAddRequest;
import com.connio.sdk.http.Request;
import com.connio.sdk.http.Response;
import com.connio.sdk.resource.deviceprofile.DeviceProfile;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.common.collect.ImmutableSet;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeviceProfileAddRequest extends ResourceAddRequest<DeviceProfile> {

    private final String name;

    private String baseProfile;

    private String description;

    private String deviceClass;

    private String vendorName;

    private String productName;

    private String imageUri;

    private ImmutableSet<String> tags;

    public DeviceProfileAddRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getBaseProfile() {
        return baseProfile;
    }

    public DeviceProfileAddRequest setBaseProfile(DeviceProfile baseProfile) {
        this.baseProfile = baseProfile.getId();
        return this;
    }

    public String getDescription() {
        return description;
    }

    public DeviceProfileAddRequest setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getDeviceClass() {
        return deviceClass;
    }

    public DeviceProfileAddRequest setDeviceClass(String deviceClass) {
        this.deviceClass = deviceClass;
        return this;
    }

    public String getVendorName() {
        return vendorName;
    }

    public DeviceProfileAddRequest setVendorName(String vendorName) {
        this.vendorName = vendorName;
        return this;
    }

    public String getProductName() {
        return productName;
    }

    public DeviceProfileAddRequest setProductName(String productName) {
        this.productName = productName;
        return this;
    }

    public String getImageUri() {
        return imageUri;
    }

    public DeviceProfileAddRequest setImageUri(String imageUri) {
        this.imageUri = imageUri;
        return this;
    }

    public ImmutableSet<String> getTags() {
        return tags;
    }

    public DeviceProfileAddRequest setTags(ImmutableSet<String> tags) {
        this.tags = tags;
        return this;
    }

    @Override
    @JsonIgnore
    protected Request request() {
        return Request.post("deviceprofiles", this);
    }

    @Override
    @JsonIgnore
    protected DeviceProfile parseEntity(Response response) {
        return response.readEntity(DeviceProfile.class);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeviceProfileAddRequest that = (DeviceProfileAddRequest) o;
        return Objects.equals(getName(), that.getName()) &&
                Objects.equals(getBaseProfile(), that.getBaseProfile()) &&
                Objects.equals(getDescription(), that.getDescription()) &&
                Objects.equals(getDeviceClass(), that.getDeviceClass()) &&
                Objects.equals(getVendorName(), that.getVendorName()) &&
                Objects.equals(getProductName(), that.getProductName()) &&
                Objects.equals(getImageUri(), that.getImageUri()) &&
                Objects.equals(getTags(), that.getTags());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getBaseProfile(), getDescription(), getDeviceClass(), getVendorName(), getProductName(), getImageUri(), getTags());
    }
}
