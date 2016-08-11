package com.connio.sdk.request.data;

import com.connio.sdk.http.Request;
import com.connio.sdk.http.Response;
import com.connio.sdk.request.ApiFetchRequest;
import com.connio.sdk.resource.data.DeviceState;
import com.connio.sdk.resource.device.Device;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Device state fetch request used to fetch a device state.
 */
public class DeviceStateFetchRequest extends ApiFetchRequest<DeviceState> {

    private final String deviceId;

    private boolean meta;

    private boolean stats;

    private boolean alerts;

    private List<String> properties;

    private List<String> methods;

    public DeviceStateFetchRequest(Device device) {
        this.deviceId = device.getId();
        this.meta = false;
        this.stats = false;
        this.alerts = false;
    }

    public boolean isMeta() {
        return meta;
    }

    public DeviceStateFetchRequest setMeta(boolean meta) {
        this.meta = meta;
        return this;
    }

    public boolean isStats() {
        return stats;
    }

    public DeviceStateFetchRequest setStats(boolean stats) {
        this.stats = stats;
        return this;
    }

    public boolean isAlerts() {
        return alerts;
    }

    public DeviceStateFetchRequest setAlerts(boolean alerts) {
        this.alerts = alerts;
        return this;
    }

    public List<String> getProperties() {
        return properties;
    }

    public DeviceStateFetchRequest setProperties(List<String> properties) {
        this.properties = properties;
        return this;
    }

    public List<String> getMethods() {
        return methods;
    }

    public DeviceStateFetchRequest setMethods(List<String> methods) {
        this.methods = methods;
        return this;
    }

    @Override
    protected Request request() {
        final Map<String, String> parameters = new HashMap<>();

        if (meta) parameters.put("meta", "true");
        if (stats) parameters.put("stats", "true");
        if (alerts) parameters.put("alerts", "true");
        if (properties != null && properties.size() > 0) parameters.put("properties", StringUtils.join(properties, ","));
        if (methods != null && methods.size() > 0) parameters.put("methods", StringUtils.join(methods, ","));

        return Request.get("data/devices/" + deviceId, parameters);
    }

    @Override
    protected DeviceState parseResourceEntity(Response response) {
        return response.readEntity(DeviceState.class);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeviceStateFetchRequest that = (DeviceStateFetchRequest) o;
        return Objects.equals(isMeta(), that.isMeta()) &&
                Objects.equals(isStats(), that.isStats()) &&
                Objects.equals(isAlerts(), that.isAlerts()) &&
                Objects.equals(deviceId, that.deviceId) &&
                Objects.equals(getProperties(), that.getProperties()) &&
                Objects.equals(getMethods(), that.getMethods());
    }

    @Override
    public int hashCode() {
        return Objects.hash(deviceId, isMeta(), isStats(), isAlerts(), getProperties(), getMethods());
    }
}
