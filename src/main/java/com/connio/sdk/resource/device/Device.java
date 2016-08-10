package com.connio.sdk.resource.device;

import com.connio.sdk.request.alert.AlertAddRequest;
import com.connio.sdk.request.alert.AlertRemoveIncidentRequest;
import com.connio.sdk.request.data.DeviceStateFetchRequest;
import com.connio.sdk.request.data.ReadDataRequest;
import com.connio.sdk.request.data.ReadMethodRequest;
import com.connio.sdk.request.data.WriteDataFeedRequest;
import com.connio.sdk.request.device.DeviceDeleteRequest;
import com.connio.sdk.request.device.DeviceFetchRequest;
import com.connio.sdk.request.device.DeviceUpdateRequest;
import com.connio.sdk.request.deviceprofile.DeviceProfileFetchRequest;
import com.connio.sdk.resource.Location;
import com.connio.sdk.resource.Resource;
import com.connio.sdk.resource.alert.Alert;
import com.connio.sdk.resource.alert.AlertCheck;
import com.connio.sdk.resource.alert.Notification;
import com.connio.sdk.resource.data.DataFeed;
import com.connio.sdk.resource.method.Method;
import com.connio.sdk.resource.property.Property;
import com.fasterxml.jackson.annotation.*;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import org.apache.commons.lang3.text.WordUtils;
import org.joda.time.DateTime;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Device extends Resource {

    /**
     * Device Status values
     */
    public enum Status {
        Enabled,
        Disabled,
        Debug;

        @JsonValue
        public String value() {
            return name().toLowerCase();
        }

        @JsonCreator
        public static Status fromValue(@JsonProperty("name") final String name) {
            return Status.valueOf(WordUtils.capitalize(name.toLowerCase()));
        }
    }

    /**
     * Custom id types
     */
    public enum CustomId {
        Sn,
        Mac,
        Imei,
        Esn;

        @JsonValue
        public String value() {
            return name().toLowerCase();
        }

        @JsonCreator
        public static CustomId fromValue(@JsonProperty("name") final String name) {
            return CustomId.valueOf(WordUtils.capitalize(name.toLowerCase()));
        }
    }

    private final String id;

    private final String name;

    private final String accountId;

    private final String deviceProfileId;

    private final String appId;

    private final Status status;

    private final Integer period;

    private final ImmutableMap<CustomId, String> customIds;

    private final String notes;

    private final String timezone;

    private final boolean annotateWithLoc;

    private final boolean annotateWithMeta;

    private final ImmutableSet<String> tags;

    private final Location location;


    public Device(@JsonProperty("id") final String id,
                  @JsonProperty("name") final String name,
                  @JsonProperty("accountId") final String accountId,
                  @JsonProperty("deviceProfileId") final String deviceProfileId,
                  @JsonProperty("appId") final String appId,
                  @JsonProperty("status") final Device.Status status,
                  @JsonProperty("period") final Integer period,
                  @JsonProperty("customIds") final ImmutableMap<CustomId, String> customIds,
                  @JsonProperty("notes") final String notes,
                  @JsonProperty("timezone") final String timezone,
                  @JsonProperty("annotateWithLoc") final Boolean annotateWithLoc,
                  @JsonProperty("annotateWithMeta") final Boolean annotateWithMeta,
                  @JsonProperty("tags") final ImmutableSet<String> tags,
                  @JsonProperty("location") final Location location,
                  @JsonProperty("dateCreated") final DateTime dateCreated,
                  @JsonProperty("dateModified") final DateTime dateModified) {

        this.id = id;
        this.name = name;
        this.accountId = accountId;
        this.deviceProfileId = deviceProfileId;
        this.appId = appId;
        this.status = status;
        this.period = period;
        this.customIds = customIds != null ? customIds : new ImmutableMap.Builder<CustomId, String>().build();
        this.notes = notes;
        this.timezone = timezone;
        this.annotateWithLoc = annotateWithLoc;
        this.annotateWithMeta = annotateWithMeta;
        this.tags = tags == null ? ImmutableSet.<String>of() : ImmutableSet.copyOf(tags);
        this.location = location;
    }


    public String getId() {
        return id;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getName() { return name; }

    public String getDeviceProfileId() {
        return deviceProfileId;
    }

    public String getAppId() {
        return appId;
    }

    public Status getStatus() {
        return status;
    }

    public Integer getPeriod() {
        return period;
    }

    public ImmutableMap<CustomId, String> getCustomIds() {
        return customIds;
    }

    public Optional<String> getNotes() {
        return Optional.ofNullable(notes);
    }

    public Optional<String> getTimezone() {
        return Optional.ofNullable(timezone);
    }

    public boolean isAnnotateWithLoc() {
        return annotateWithLoc;
    }

    public boolean isAnnotateWithMeta() {
        return annotateWithMeta;
    }

    public ImmutableSet<String> getTags() {
        return tags;
    }

    public Optional<Location> getLocation() {
        return Optional.ofNullable(location);
    }

    public static DeviceFetchRequest find(String deviceId) {
        return new DeviceFetchRequest(deviceId);
    }

    public DeviceUpdateRequest update() {
        return new DeviceUpdateRequest(this);
    }

    public DeviceDeleteRequest delete() {
        return new DeviceDeleteRequest(id);
    }

    public DeviceProfileFetchRequest deviceProfile() {
        return new DeviceProfileFetchRequest(this.getDeviceProfileId());
    }

    public ReadDataRequest readData(Property property) {
        return new ReadDataRequest(this, property);
    }

    public WriteDataFeedRequest writeData(Property property, DataFeed dataFeed) {
        return new WriteDataFeedRequest(this, property, dataFeed);
    }

    public WriteDataFeedRequest writeData(DataFeed dataFeed) {
        return new WriteDataFeedRequest(this, dataFeed);
    }

    public ReadMethodRequest readMethod(Method method) {
        return new ReadMethodRequest(this, method);
    }

    public DeviceStateFetchRequest state() {
        return new DeviceStateFetchRequest(this);
    }

    public AlertAddRequest addAlert(String name, String triggerPropId, String metric, ImmutableList<AlertCheck> checks,
                                    ImmutableList<Notification> notifications) {

        return new AlertAddRequest(this, name, triggerPropId, metric, checks, notifications);
    }

    public AlertRemoveIncidentRequest removeIncident(Alert alert) {
        return new AlertRemoveIncidentRequest(this, alert);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        Device other = (Device) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, accountId, name);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper("Device")
                .add("id", id)
                .add("name", name)
                .add("tags", getTags())
                .add("deviceProfileId", getDeviceProfileId())
                .add("appId", getAppId())
                .add("accountId", getAccountId())
                .toString();
    }
}
