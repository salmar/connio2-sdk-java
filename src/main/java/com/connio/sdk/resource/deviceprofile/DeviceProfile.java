package com.connio.sdk.resource.deviceprofile;

import com.connio.sdk.request.device.DeviceAddRequest;
import com.connio.sdk.request.deviceprofile.DeviceProfileAddRequest;
import com.connio.sdk.request.deviceprofile.DeviceProfileDeleteRequest;
import com.connio.sdk.request.deviceprofile.DeviceProfileUpdateRequest;
import com.connio.sdk.request.method.MethodAddRequest;
import com.connio.sdk.request.property.PropertyAddRequest;
import com.connio.sdk.resource.Resource;
import com.connio.sdk.resource.method.Method;
import com.connio.sdk.resource.method.MethodImpl;
import com.connio.sdk.resource.property.Property;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableSet;
import org.joda.time.DateTime;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeviceProfile extends Resource {

    private final String id;

    private final String name;

    private final String baseProfile;

    private final String description;

    private final String deviceClass;

    private final String vendorName;

    private final String productName;

    private final String imageUri;

    private final ImmutableSet<String> tags;

    private final String accountId;

    public DeviceProfile(@JsonProperty("id") final String id,
                         @JsonProperty("name") final String name,
                         @JsonProperty("accountId") final String accountId,
                         @JsonProperty("baseProfile") final String baseProfile,
                         @JsonProperty("description") final String description,
                         @JsonProperty("deviceClass") final String deviceClass,
                         @JsonProperty("vendorName") final String vendorName,
                         @JsonProperty("productName") final String productName,
                         @JsonProperty("imageUri") final String imageUri,
                         @JsonProperty("tags")final ImmutableSet<String> tags,
                         @JsonProperty("dateCreated") final DateTime dateCreated,
                         @JsonProperty("dateModified") final DateTime dateModified) {

        this.id = id;
        this.name = name;
        this.accountId = accountId;
        this.baseProfile = baseProfile;
        this.description = description;
        this.deviceClass = deviceClass;
        this.vendorName = vendorName;
        this.productName = productName;
        this.imageUri = imageUri;
        this.tags = tags == null ? ImmutableSet.<String>of() : ImmutableSet.copyOf(tags);
    }


    public String getId() {
        return id;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getBaseProfile() {
        return baseProfile;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getDeviceClass() {
        return deviceClass;
    }

    public String getVendorName() {
        return vendorName;
    }

    public String getProductName() {
        return productName;
    }

    public String getImageUri() {
        return imageUri;
    }

    public ImmutableSet<String> getTags() {
        return tags;
    }

    public DeviceAddRequest addDevice() {
        return new DeviceAddRequest(this);
    }

    public static DeviceProfileAddRequest create(String name) {
        return new DeviceProfileAddRequest(name);
    }

    public DeviceProfileUpdateRequest update() {
        return new DeviceProfileUpdateRequest(this);
    }

    public DeviceProfileDeleteRequest delete() {
        return new DeviceProfileDeleteRequest(this);
    }

    public PropertyAddRequest addProperty(String name, Property.Type type) {
        return new PropertyAddRequest(this, name, type);
    }

    public MethodAddRequest addMethod(String name, Method.Access access, MethodImpl implementation) {
        return new MethodAddRequest(this, name, access, implementation);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        DeviceProfile other = (DeviceProfile) obj;
        return  Objects.equals(this.getId(), other.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(),
                getAccountId(),
                getName(),
                getDeviceClass(),
                getProductName(),
                getVendorName());
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper("Device Profile")
                .add("id", getId())
                .add("name", getName())
                .add("description", getDescription())
                .add("deviceClass", getDeviceClass())
                .add("deviceVendor", getVendorName())
                .add("productName", getProductName())
                .add("imageUri", getImageUri())
                .add("tags", getTags())
                .add("accountId", getAccountId())
                .toString();
    }
}

