/**
 * Copyright (c) 2016 Connio Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Authors: juan@connio.com
 *
 */

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

import java.util.Objects;

/**
 * Device update request used to update a device.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeviceUpdateRequest extends ResourceUpdateRequest<Device> {

    private final Device device;

    private String name;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeviceUpdateRequest that = (DeviceUpdateRequest) o;
        return Objects.equals(device, that.device) &&
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(getCustomIds(), that.getCustomIds()) &&
                Objects.equals(getNotes(), that.getNotes()) &&
                Objects.equals(getStatus(), that.getStatus()) &&
                Objects.equals(getTags(), that.getTags()) &&
                Objects.equals(getTimezone(), that.getTimezone()) &&
                Objects.equals(getAnnotateWithLoc(), that.getAnnotateWithLoc()) &&
                Objects.equals(getAnnotateWithMeta(), that.getAnnotateWithMeta()) &&
                Objects.equals(getPeriod(), that.getPeriod()) &&
                Objects.equals(getLocation(), that.getLocation());
    }

    @Override
    public int hashCode() {
        return Objects.hash(device, getName(), getCustomIds(), getNotes(), getStatus(), getTags(),
                getTimezone(), getAnnotateWithLoc(), getAnnotateWithMeta(), getPeriod(), getLocation());
    }
}
