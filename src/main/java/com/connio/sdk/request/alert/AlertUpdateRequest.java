package com.connio.sdk.request.alert;

import com.connio.sdk.request.ResourceUpdateRequest;
import com.connio.sdk.http.Request;
import com.connio.sdk.http.Response;
import com.connio.sdk.resource.alert.Alert;
import com.connio.sdk.resource.alert.AlertCheck;
import com.connio.sdk.resource.alert.AlertHandler;
import com.connio.sdk.resource.alert.Notification;
import com.connio.sdk.resource.device.Device;
import com.connio.sdk.resource.deviceprofile.DeviceProfile;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AlertUpdateRequest extends ResourceUpdateRequest<Alert> {

    private final String ownerId;

    private final String alertId;

    private String name;

    private String triggerPropId;

    private String metric;

    private ImmutableList<AlertCheck> checks;

    private ImmutableList<Notification> notifications;

    private String description;

    private ImmutableSet<String> tags;

    private Alert.Status status;

    private AlertHandler recover;

    public AlertUpdateRequest(DeviceProfile deviceProfile, String alertId) {
        this.ownerId = deviceProfile.getId();
        this.alertId = alertId;
    }

    public AlertUpdateRequest(Device device, String alertId) {
        this.ownerId = device.getId();
        this.alertId = alertId;
    }

    public AlertUpdateRequest(String ownerId, String alertId) {
        this.ownerId = ownerId;
        this.alertId = alertId;

        if (ownerId == null || (!ownerId.startsWith("_dpf_") && !ownerId.startsWith("_dev_")))
            throw new IllegalArgumentException("Invalid ownerId");
    }

    public String getOwnerId() {
        return ownerId;
    }

    public String getAlertId() {
        return alertId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTriggerPropId() {
        return triggerPropId;
    }

    public AlertUpdateRequest setTriggerPropId(String triggerPropId) {
        this.triggerPropId = triggerPropId;
        return this;
    }

    public String getMetric() {
        return metric;
    }

    public AlertUpdateRequest setMetric(String metric) {
        this.metric = metric;
        return this;
    }

    public ImmutableList<AlertCheck> getChecks() {
        return checks;
    }

    public AlertUpdateRequest setChecks(ImmutableList<AlertCheck> checks) {
        this.checks = checks;
        return this;
    }

    public ImmutableList<Notification> getNotifications() {
        return notifications;
    }

    public AlertUpdateRequest setNotifications(ImmutableList<Notification> notifications) {
        this.notifications = notifications;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public AlertUpdateRequest setDescription(String description) {
        this.description = description;
        return this;
    }

    public ImmutableSet<String> getTags() {
        return tags;
    }

    public AlertUpdateRequest setTags(ImmutableSet<String> tags) {
        this.tags = tags;
        return this;
    }

    public Alert.Status getStatus() {
        return status;
    }

    public AlertUpdateRequest setStatus(Alert.Status status) {
        this.status = status;
        return this;
    }

    public AlertHandler getRecover() {
        return recover;
    }

    public AlertUpdateRequest setRecover(AlertHandler recover) {
        this.recover = recover;
        return this;
    }

    @Override
    protected Request request() {
        final String ownerPath = (ownerId.startsWith("_dpf_")) ? "deviceprofiles" : "devices";
        final String path = ownerPath + "/" + ownerId + "/alerts/" + alertId;
        return Request.put(path, this);
    }

    @Override
    protected Alert parseEntity(Response response) {
        return response.readEntity(Alert.class);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AlertUpdateRequest that = (AlertUpdateRequest) o;
        return Objects.equals(getOwnerId(), that.getOwnerId()) &&
                Objects.equals(getAlertId(), that.getAlertId()) &&
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(getTriggerPropId(), that.getTriggerPropId()) &&
                Objects.equals(getMetric(), that.getMetric()) &&
                Objects.equals(getChecks(), that.getChecks()) &&
                Objects.equals(getNotifications(), that.getNotifications()) &&
                Objects.equals(getDescription(), that.getDescription()) &&
                Objects.equals(getTags(), that.getTags()) &&
                Objects.equals(getStatus(), that.getStatus()) &&
                Objects.equals(getRecover(), that.getRecover());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOwnerId(), getAlertId(), getName(), getTriggerPropId(), getMetric(), getChecks(),
                getNotifications(), getDescription(), getTags(), getStatus(), getRecover());
    }
}
