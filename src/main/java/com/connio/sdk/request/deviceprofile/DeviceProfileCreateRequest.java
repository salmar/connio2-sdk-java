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

package com.connio.sdk.request.deviceprofile;

import com.connio.sdk.request.ResourceCreateRequest;
import com.connio.sdk.http.Request;
import com.connio.sdk.http.Response;
import com.connio.sdk.resource.deviceprofile.DeviceProfile;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.common.collect.ImmutableSet;

import java.util.Objects;

/**
 * Device profile create request used to create new device profiles.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeviceProfileCreateRequest extends ResourceCreateRequest<DeviceProfile> {

    private final String name;

    private String baseProfile;

    private String description;

    private String deviceClass;

    private String vendorName;

    private String productName;

    private String imageUri;

    private ImmutableSet<String> tags;

    public DeviceProfileCreateRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getBaseProfile() {
        return baseProfile;
    }

    public DeviceProfileCreateRequest setBaseProfile(DeviceProfile baseProfile) {
        this.baseProfile = baseProfile.getId();
        return this;
    }

    public String getDescription() {
        return description;
    }

    public DeviceProfileCreateRequest setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getDeviceClass() {
        return deviceClass;
    }

    public DeviceProfileCreateRequest setDeviceClass(String deviceClass) {
        this.deviceClass = deviceClass;
        return this;
    }

    public String getVendorName() {
        return vendorName;
    }

    public DeviceProfileCreateRequest setVendorName(String vendorName) {
        this.vendorName = vendorName;
        return this;
    }

    public String getProductName() {
        return productName;
    }

    public DeviceProfileCreateRequest setProductName(String productName) {
        this.productName = productName;
        return this;
    }

    public String getImageUri() {
        return imageUri;
    }

    public DeviceProfileCreateRequest setImageUri(String imageUri) {
        this.imageUri = imageUri;
        return this;
    }

    public ImmutableSet<String> getTags() {
        return tags;
    }

    public DeviceProfileCreateRequest setTags(ImmutableSet<String> tags) {
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
        DeviceProfileCreateRequest that = (DeviceProfileCreateRequest) o;
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
