package com.connio.sdk.resource.data;

import com.connio.sdk.resource.Location;
import com.connio.sdk.resource.device.Device;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;
import java.util.Optional;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeviceState {

    private final String id;

    private final String name;

    private final String accountId;

    private final String datastoreId;

    private final String deviceProfileId;

    private final String appId;

    private final Integer rateLimit;

    private final Integer maxNumOfDpPerFeed;

    private final String deviceClass;

    private final String vendorName;

    private final String productName;

    private final String imageUri;

    private final Device.Status status;

    private final Integer period;

    private final ImmutableMap<Device.CustomId, String> customIds;

    private final String notes;

    private final String timezone;

    private final ImmutableSet<String> tags;

    private final Location location;

    private final Boolean annotateWithLoc;

    private final Boolean annotateWithMeta;

    private final ImmutableSet<PropertyInfo> properties;

    private final ImmutableSet<MethodInfo> methods;

    private final ImmutableSet<AlertInfo> alerts;


    public DeviceState(@JsonProperty("id") final String id,
                       @JsonProperty("name") final String name,
                       @JsonProperty("accountId") final String accountId,
                       @JsonProperty("datastoreId") final String datastoreId,
                       @JsonProperty("deviceProfileId") final String deviceProfileId,
                       @JsonProperty("appId") final String appId,
                       @JsonProperty("rateLimit") final Integer rateLimit,
                       @JsonProperty("maxNumOfDpPerFeed") final Integer maxNumOfDpPerFeed,
                       @JsonProperty("deviceClass") final String deviceClass,
                       @JsonProperty("vendorName") final String vendorName,
                       @JsonProperty("productName") final String productName,
                       @JsonProperty("imageUri") final String imageUri,
                       @JsonProperty("status") final Device.Status status,
                       @JsonProperty("period") final Integer period,
                       @JsonProperty("customIds") final ImmutableMap<Device.CustomId, String> customIds,
                       @JsonProperty("notes") final String notes,
                       @JsonProperty("timezone") final String timezone,
                       @JsonProperty("tags") final ImmutableSet<String> tags,
                       @JsonProperty("location") final Location location,
                       @JsonProperty("annotateWithLoc") final Boolean annotateWithLoc,
                       @JsonProperty("annotateWithMeta") final Boolean annotateWithMeta,
                       @JsonProperty("properties") final ImmutableSet<PropertyInfo> properties,
                       @JsonProperty("methods") final ImmutableSet<MethodInfo> methods,
                       @JsonProperty("alerts") final ImmutableSet<AlertInfo> alerts) {

        this.id = id;
        this.name = name;
        this.accountId = accountId;
        this.datastoreId = datastoreId;
        this.deviceProfileId = deviceProfileId;
        this.appId = appId;
        this.rateLimit = rateLimit;
        this.maxNumOfDpPerFeed = maxNumOfDpPerFeed;
        this.deviceClass = deviceClass;
        this.vendorName = vendorName;
        this.productName = productName;
        this.imageUri = imageUri;
        this.status = status;
        this.period = period;
        this.customIds = customIds != null ? customIds : ImmutableMap.of();
        this.notes = notes;
        this.timezone = timezone;
        this.tags = tags != null ? tags : ImmutableSet.of();
        this.location = location;
        this.annotateWithLoc = annotateWithLoc;
        this.annotateWithMeta = annotateWithMeta;
        this.properties = properties != null ? properties : ImmutableSet.of();
        this.methods = methods != null ? methods : ImmutableSet.of();
        this.alerts = alerts != null ? alerts : ImmutableSet.of();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getDatastoreId() {
        return datastoreId;
    }

    public String getDeviceProfileId() {
        return deviceProfileId;
    }

    public String getAppId() {
        return appId;
    }

    public Integer getRateLimit() {
        return rateLimit;
    }

    public Integer getMaxNumOfDpPerFeed() {
        return maxNumOfDpPerFeed;
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

    public Device.Status getStatus() {
        return status;
    }

    public Integer getPeriod() {
        return period;
    }

    public ImmutableMap<Device.CustomId, String> getCustomIds() {
        return customIds;
    }

    public Optional<String> getNotes() {
        return Optional.ofNullable(notes);
    }

    public Optional<String> getTimezone() {
        return Optional.ofNullable(timezone);
    }

    public ImmutableSet<String> getTags() {
        return tags;
    }

    public Optional<Location> getLocation() {
        return Optional.ofNullable(location);
    }

    public Boolean getAnnotateWithLoc() {
        return annotateWithLoc;
    }

    public Boolean getAnnotateWithMeta() {
        return annotateWithMeta;
    }

    public ImmutableSet<PropertyInfo> getProperties() {
        return properties;
    }

    public ImmutableSet<MethodInfo> getMethods() {
        return methods;
    }

    public ImmutableSet<AlertInfo> getAlerts() {
        return alerts;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        DeviceState other = (DeviceState) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper("DeviceState")
                .add("id", id)
                .add("name", name)
                .add("properties", StringUtils.join(properties, ","))
                .add("methods", StringUtils.join(methods, ","))
                .add("alerts", StringUtils.join(alerts, ","))
                .toString();
    }
}
