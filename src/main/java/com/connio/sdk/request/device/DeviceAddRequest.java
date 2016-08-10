package com.connio.sdk.request.device;

import com.connio.sdk.request.ResourceAddRequest;
import com.connio.sdk.http.Request;
import com.connio.sdk.http.Response;
import com.connio.sdk.resource.device.Device;
import com.connio.sdk.resource.Location;
import com.connio.sdk.resource.deviceprofile.DeviceProfile;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeviceAddRequest extends ResourceAddRequest<Device> {

    private String name;

    private final String deviceProfile;

    private Device.Status status;

    private Integer period;

    private String timezone;

    private Boolean annotateWithLoc;

    private Boolean annotateWithMeta;

    private ImmutableMap<Device.CustomId, String> customIds;

    private String notes;

    private Location location;

    private ImmutableSet<String> tags;


    public DeviceAddRequest(DeviceProfile deviceProfile) {
        this.deviceProfile = deviceProfile.getId();
    }

    public String getName() {
        return name;
    }

    public DeviceAddRequest setName(String name) {
        this.name = name;
        return this;
    }

    public String getDeviceProfile() {
        return deviceProfile;
    }

    public Device.Status getStatus() {
        return status;
    }

    public DeviceAddRequest setStatus(Device.Status status) {
        this.status = status;
        return this;
    }

    public Integer getPeriod() {
        return period;
    }

    public DeviceAddRequest setPeriod(Integer period) {
        this.period = period;
        return this;
    }

    public String getTimezone() {
        return timezone;
    }

    public DeviceAddRequest setTimezone(String timezone) {
        this.timezone = timezone;
        return this;
    }

    public Boolean getAnnotateWithLoc() {
        return annotateWithLoc;
    }

    public DeviceAddRequest setAnnotateWithLoc(Boolean annotateWithLoc) {
        this.annotateWithLoc = annotateWithLoc;
        return this;
    }

    public Boolean getAnnotateWithMeta() {
        return annotateWithMeta;
    }

    public DeviceAddRequest setAnnotateWithMeta(Boolean annotateWithMeta) {
        this.annotateWithMeta = annotateWithMeta;
        return this;
    }

    public ImmutableMap<Device.CustomId, String> getCustomIds() {
        return customIds;
    }

    public DeviceAddRequest setCustomIds(ImmutableMap<Device.CustomId, String> customIds) {
        this.customIds = customIds;
        return this;
    }

    public String getNotes() {
        return notes;
    }

    public DeviceAddRequest setNotes(String notes) {
        this.notes = notes;
        return this;
    }

    public Location getLocation() {
        return location;
    }

    public DeviceAddRequest setLocation(Location location) {
        this.location = location;
        return this;
    }

    public ImmutableSet<String> getTags() {
        return tags;
    }

    public DeviceAddRequest setTags(ImmutableSet<String> tags) {
        this.tags = tags;
        return this;
    }

    @Override
    @JsonIgnore
    protected Request request() {
        return Request.post("devices", this);
    }

    @Override
    @JsonIgnore
    protected Device parseEntity(Response response) {
        return response.readEntity(Device.class);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeviceAddRequest that = (DeviceAddRequest) o;
        return Objects.equals(getName(), that.getName()) &&
                Objects.equals(getDeviceProfile(), that.getDeviceProfile()) &&
                Objects.equals(getStatus(), that.getStatus()) &&
                Objects.equals(getPeriod(), that.getPeriod()) &&
                Objects.equals(getTimezone(), that.getTimezone()) &&
                Objects.equals(getAnnotateWithLoc(), that.getAnnotateWithLoc()) &&
                Objects.equals(getAnnotateWithMeta(), that.getAnnotateWithMeta()) &&
                Objects.equals(getCustomIds(), that.getCustomIds()) &&
                Objects.equals(getNotes(), that.getNotes()) &&
                Objects.equals(getLocation(), that.getLocation()) &&
                Objects.equals(getTags(), that.getTags());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getDeviceProfile(), getStatus(), getPeriod(), getTimezone(),
                getAnnotateWithLoc(), getAnnotateWithMeta(), getCustomIds(), getNotes(), getLocation(), getTags());
    }
}