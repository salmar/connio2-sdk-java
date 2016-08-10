package com.connio.sdk.request.device;

import com.connio.sdk.request.ResourceUpdateRequest;
import com.connio.sdk.http.Request;
import com.connio.sdk.http.Response;
import com.connio.sdk.resource.device.Device;
import com.connio.sdk.resource.Location;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeviceUpdateRequest extends ResourceUpdateRequest<Device> {

    private final Device device;

    private String name;

    private String app;

    private ImmutableMap<Device.CustomId, String> customIds;

    private String notes;

    private Device.Status status;

    private ImmutableSet<String> tags;

    private String timezone;

    private Boolean annotateWithLoc;

    private Boolean annotateWithMeta;

    private Integer period;

    private Location location;

    public DeviceUpdateRequest(Device device) {
        this.device = device;
    }

    public String getName() {
        return name;
    }

    public DeviceUpdateRequest setName(String name) {
        this.name = name;
        return this;
    }

    public String getApp() {
        return app;
    }

    public DeviceUpdateRequest setApp(String app) {
        this.app = app;
        return this;
    }

    public ImmutableMap<Device.CustomId, String> getCustomIds() {
        return customIds;
    }

    public DeviceUpdateRequest setCustomIds(ImmutableMap<Device.CustomId, String> customIds) {
        this.customIds = customIds;
        return this;
    }

    public String getNotes() {
        return notes;
    }

    public DeviceUpdateRequest setNotes(String notes) {
        this.notes = notes;
        return this;
    }

    public Device.Status getStatus() {
        return status;
    }

    public DeviceUpdateRequest setStatus(Device.Status status) {
        this.status = status;
        return this;
    }

    public ImmutableSet<String> getTags() {
        return tags;
    }

    public DeviceUpdateRequest setTags(ImmutableSet<String> tags) {
        this.tags = tags;
        return this;
    }

    public String getTimezone() {
        return timezone;
    }

    public DeviceUpdateRequest setTimezone(String timezone) {
        this.timezone = timezone;
        return this;
    }

    public Boolean getAnnotateWithLoc() {
        return annotateWithLoc;
    }

    public DeviceUpdateRequest setAnnotateWithLoc(Boolean annotateWithLoc) {
        this.annotateWithLoc = annotateWithLoc;
        return this;
    }

    public Boolean getAnnotateWithMeta() {
        return annotateWithMeta;
    }

    public DeviceUpdateRequest setAnnotateWithMeta(Boolean annotateWithMeta) {
        this.annotateWithMeta = annotateWithMeta;
        return this;
    }

    public Integer getPeriod() {
        return period;
    }

    public DeviceUpdateRequest setPeriod(Integer period) {
        this.period = period;
        return this;
    }

    public Location getLocation() {
        return location;
    }

    public DeviceUpdateRequest setLocation(Location location) {
        this.location = location;
        return this;
    }

    @Override
    @JsonIgnore
    protected Request request() {
        return Request.put("devices/" + device.getId(), this);
    }

    @Override
    @JsonIgnore
    protected Device parseEntity(Response response) {
        return response.readEntity(Device.class);
    }
}
