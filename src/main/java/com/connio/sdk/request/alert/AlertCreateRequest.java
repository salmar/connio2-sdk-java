package com.connio.sdk.request.alert;

import com.connio.sdk.http.Request;
import com.connio.sdk.http.Response;
import com.connio.sdk.request.ResourceCreateRequest;
import com.connio.sdk.resource.alert.Alert;
import com.connio.sdk.resource.alert.AlertCheck;
import com.connio.sdk.resource.alert.AlertHandler;
import com.connio.sdk.resource.alert.Notification;
import com.connio.sdk.resource.device.Device;
import com.connio.sdk.resource.deviceprofile.DeviceProfile;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

import java.util.Objects;

/**
 * Alert create request used to create new alerts.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AlertCreateRequest extends ResourceCreateRequest<Alert> {

    private final String ownerId;

    private final String name;

    private final String triggerPropId;

    private final String metric;

    private final ImmutableList<AlertCheck> checks;

    private final ImmutableList<Notification> notifications;

    private String description;

    private ImmutableSet<String> tags;

    private Alert.Status status;

    private AlertHandler recover;

    public AlertCreateRequest(DeviceProfile deviceProfile, String name, String triggerPropId, String metric,
                              ImmutableList<AlertCheck> checks, ImmutableList<Notification> notifications) {

        this.ownerId = deviceProfile.getId();
        this.name = name;
        this.triggerPropId = triggerPropId;
        this.metric = metric;
        this.checks = checks;
        this.notifications = notifications;
    }

    public AlertCreateRequest(Device device, String name, String triggerPropId, String metric,
                              ImmutableList<AlertCheck> checks, ImmutableList<Notification> notifications) {

        this.ownerId = device.getId();
        this.name = name;
        this.triggerPropId = triggerPropId;
        this.metric = metric;
        this.checks = checks;
        this.notifications = notifications;
    }

    public String getName() {
        return name;
    }

    public String getTriggerPropId() {
        return triggerPropId;
    }

    public String getMetric() {
        return metric;
    }

    public ImmutableList<AlertCheck> getChecks() {
        return checks;
    }

    public ImmutableList<Notification> getNotifications() {
        return notifications;
    }

    public String getDescription() {
        return description;
    }

    public AlertCreateRequest setDescription(String description) {
        this.description = description;
        return this;
    }

    public ImmutableSet<String> getTags() {
        return tags;
    }

    public AlertCreateRequest setTags(ImmutableSet<String> tags) {
        this.tags = tags;
        return this;
    }

    public Alert.Status getStatus() {
        return status;
    }

    public AlertCreateRequest setStatus(Alert.Status status) {
        this.status = status;
        return this;
    }

    public AlertHandler getRecover() {
        return recover;
    }

    public AlertCreateRequest setRecover(AlertHandler recover) {
        this.recover = recover;
        return this;
    }

    @Override
    protected Request request() {
        final String ownerPath = (ownerId.startsWith("_dpf_")) ? "deviceprofiles" : "devices";
        final String path = ownerPath + "/" + ownerId + "/alerts";

        return Request.post(path, this);
    }

    @Override
    protected Alert parseEntity(Response response) {
        return response.readEntity(Alert.class);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AlertCreateRequest that = (AlertCreateRequest) o;
        return Objects.equals(ownerId, that.ownerId) &&
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
        return Objects.hash(ownerId, getName(), getTriggerPropId(), getMetric(), getChecks(), getNotifications(),
                getDescription(), getTags(), getStatus(), getRecover());
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper("AlertCreateRequest")
                .add("ownerId", ownerId)
                .add("name", name)
                .add("triggerPropId", triggerPropId)
                .add("metric", metric)
                .add("checks", checks)
                .add("notifications", notifications)
                .add("description", description)
                .add("tags", tags)
                .add("status", status)
                .add("recover", recover)
                .toString();
    }
}
