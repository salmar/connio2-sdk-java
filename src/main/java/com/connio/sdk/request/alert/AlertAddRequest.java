package com.connio.sdk.request.alert;

import com.connio.sdk.request.ResourceAddRequest;
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

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AlertAddRequest extends ResourceAddRequest<Alert> {

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

    public AlertAddRequest(DeviceProfile deviceProfile, String name, String triggerPropId, String metric,
                           ImmutableList<AlertCheck> checks, ImmutableList<Notification> notifications) {

        this.ownerId = deviceProfile.getId();
        this.name = name;
        this.triggerPropId = triggerPropId;
        this.metric = metric;
        this.checks = checks;
        this.notifications = notifications;
    }

    public AlertAddRequest(Device device, String name, String triggerPropId, String metric,
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

    public AlertAddRequest setDescription(String description) {
        this.description = description;
        return this;
    }

    public ImmutableSet<String> getTags() {
        return tags;
    }

    public AlertAddRequest setTags(ImmutableSet<String> tags) {
        this.tags = tags;
        return this;
    }

    public Alert.Status getStatus() {
        return status;
    }

    public AlertAddRequest setStatus(Alert.Status status) {
        this.status = status;
        return this;
    }

    public AlertHandler getRecover() {
        return recover;
    }

    public AlertAddRequest setRecover(AlertHandler recover) {
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
}
