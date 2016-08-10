package com.connio.sdk.resource.deviceprofile;

import com.connio.sdk.request.alert.AlertCreateRequest;
import com.connio.sdk.request.device.DeviceCreateRequest;
import com.connio.sdk.request.deviceprofile.DeviceProfileCreateRequest;
import com.connio.sdk.request.deviceprofile.DeviceProfileDeleteRequest;
import com.connio.sdk.request.deviceprofile.DeviceProfileFetchRequest;
import com.connio.sdk.request.deviceprofile.DeviceProfileUpdateRequest;
import com.connio.sdk.request.method.MethodCreateRequest;
import com.connio.sdk.request.property.PropertyCreateRequest;
import com.connio.sdk.resource.Resource;
import com.connio.sdk.resource.alert.AlertCheck;
import com.connio.sdk.resource.alert.Notification;
import com.connio.sdk.resource.method.Method;
import com.connio.sdk.resource.method.MethodImpl;
import com.connio.sdk.resource.property.Property;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import org.joda.time.DateTime;

import java.util.Objects;
import java.util.Optional;

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

    public Optional<String> getBaseProfile() {
        return Optional.ofNullable(baseProfile);
    }

    public String getName() {
        return name;
    }

    public Optional<String> getDescription() {
        return Optional.ofNullable(description);
    }

    public Optional<String> getDeviceClass() {
        return Optional.ofNullable(deviceClass);
    }

    public Optional<String> getVendorName() {
        return Optional.ofNullable(vendorName);
    }

    public Optional<String> getProductName() {
        return Optional.ofNullable(productName);
    }

    public Optional<String> getImageUri() {
        return Optional.ofNullable(imageUri);
    }

    public ImmutableSet<String> getTags() {
        return tags;
    }

    public static DeviceProfileFetchRequest fetch(String deviceProfileId) {
        return new DeviceProfileFetchRequest(deviceProfileId);
    }

    public static DeviceProfileCreateRequest create(String name) {
        return new DeviceProfileCreateRequest(name);
    }

    @Override
    public DeviceProfileUpdateRequest update() {
        return new DeviceProfileUpdateRequest(this);
    }

    @Override
    public DeviceProfileDeleteRequest delete() {
        return new DeviceProfileDeleteRequest(this);
    }

    public DeviceCreateRequest addDevice() {
        return new DeviceCreateRequest(this);
    }

    public PropertyCreateRequest addProperty(String name, Property.Type type) {
        return new PropertyCreateRequest(this, name, type);
    }

    public MethodCreateRequest addMethod(String name, Method.Access access, MethodImpl implementation) {
        return new MethodCreateRequest(this, name, access, implementation);
    }

    public AlertCreateRequest addAlert(String name, String triggerPropId, String metric, ImmutableList<AlertCheck> checks,
                                    ImmutableList<Notification> notifications) {

        return new AlertCreateRequest(this, name, triggerPropId, metric, checks, notifications);
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

