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

package com.connio.sdk.resource.device;

import com.connio.sdk.request.alert.AlertCreateRequest;
import com.connio.sdk.request.alert.AlertRemoveIncidentRequest;
import com.connio.sdk.request.data.*;
import com.connio.sdk.request.device.DeviceCreateRequest;
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
import com.connio.sdk.resource.deviceprofile.DeviceProfile;
import com.connio.sdk.resource.method.Method;
import com.connio.sdk.resource.property.Property;
import com.fasterxml.jackson.annotation.*;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import org.apache.commons.lang3.text.WordUtils;
import org.joda.time.DateTime;

import java.util.Objects;
import java.util.Optional;

/**
 * Device resource
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Device extends Resource<DeviceUpdateRequest, DeviceDeleteRequest> {

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

    /**
     * Creates an AccountFetchRequest to fetch an account given its id.
     * @param deviceId of the device that wants to be fetched.
     * @return DeviceFetchRequest.
     */
    public static DeviceFetchRequest fetch(String deviceId) {
        return new DeviceFetchRequest(deviceId);
    }

    /**
     * Creates and initialises a DeviceCreateRequest with minimum data to create a new device. Note that the returning
     * DeviceCreateRequest can be completed with all desired information that the request permits before executing it.
     * @param deviceProfile
     * @return DeviceCreateRequest
     */
    public static DeviceCreateRequest create(DeviceProfile deviceProfile) {
        return new DeviceCreateRequest(deviceProfile);
    }

    /**
     * Return a DeviceUpdateRequest for the current device to be completed with the data that wants to be updated
     * before executing the request.
     * @return DeviceUpdateRequest.
     */
    @Override
    public DeviceUpdateRequest update() {
        return new DeviceUpdateRequest(this);
    }

    /**
     * Return a DeviceDeleteRequest for the current device in order to delete it when executing.
     * @return DeviceDeleteRequest.
     */
    @Override
    public DeviceDeleteRequest delete() {
        return new DeviceDeleteRequest(id);
    }

    /**
     * Returns a DeviceProfileFetchRequest pointing to the device's device profile ready to be fetched / executed.
     * @return DeviceProfileFetchRequest.
     */
    public DeviceProfileFetchRequest deviceProfile() {
        return new DeviceProfileFetchRequest(this.getDeviceProfileId());
    }

    /**
     * Returns a ReadDataRequest to read current device specified property data. ReadDataRequest can be completed to add
     * pagination or filter parameters in order to query the desired data set.
     * @param property from which the data will be read.
     * @return ReadDataRequest
     */
    public ReadDataRequest readData(Property property) {
        return new ReadDataRequest(this, property);
    }

    /**
     * Returns a WriteDataFeedRequest to write current device specified property data when executed.
     * @param property where the data is going to be written.
     * @param dataFeed that will be written.
     * @return WriteDataFeedRequest
     */
    public WriteDataFeedRequest writeData(Property property, DataFeed dataFeed) {
        return new WriteDataFeedRequest(this, property, dataFeed);
    }

    /**
     * Returns a WriteDataFeedRequest to write current device properties that will be specified in the dataFeed object.
     * @param dataFeed that will be written.
     * @return WriteDataFeedRequest.
     */
    public WriteDataFeedRequest writeData(DataFeed dataFeed) {
        return new WriteDataFeedRequest(this, dataFeed);
    }

    /**
     * Returns ReadPublicMethodRequest to read a current device public method getter when executed.
     * @param method that wants to be read.
     * @return ReadPublicMethodRequest
     */
    public ReadPublicMethodRequest readMethod(Method method) {
        return new ReadPublicMethodRequest(this, method);
    }

    /**
     * Returns ReadPublicMethodRequest to execute a current device public method. Note that in case it is needed to
     * specify a parameter it can be done by completing the request before executing it.
     * @param method that wants to be executed.
     * @return ExecutePublicMethodRequest.
     */
    public ExecutePublicMethodRequest executeMethod(Method method) {
        return new ExecutePublicMethodRequest(this, method);
    }

    /**
     * Returns DeviceStateFetchRequest to read the current device state when fetching / executing it. Note that the
     * request can be further completed to include representation parameters before executing it.
     * @return DeviceStateFetchRequest.
     */
    public DeviceStateFetchRequest state() {
        return new DeviceStateFetchRequest(this);
    }

    /**
     * Creates and initialises a AlertCreateRequest with minimum data to create a new alert belonging to the current device.
     * Note that the returning AlertCreateRequest can be completed with all desired information that the request permits
     * before executing it.
     * @param name
     * @param triggerPropId
     * @param metric
     * @param checks
     * @param notifications
     * @return AlertCreateRequest.
     */
    public AlertCreateRequest addAlert(String name, String triggerPropId, String metric, ImmutableList<AlertCheck> checks,
                                    ImmutableList<Notification> notifications) {

        return new AlertCreateRequest(this, name, triggerPropId, metric, checks, notifications);
    }

    /**
     * Returns a AlertRemoveIncidentRequest to remove an incident on the given alert of the current device when executing.
     * @param alert the incident wants to be removed.
     * @return AlertRemoveIncidentRequest
     */
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
