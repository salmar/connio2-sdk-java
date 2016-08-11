package com.connio.sdk.request.device;

import com.connio.sdk.RandomAccessResultPage;
import com.connio.sdk.http.Request;
import com.connio.sdk.http.Response;
import com.connio.sdk.request.ResourcePaginatedReadRequest;
import com.connio.sdk.resource.device.Device;
import org.apache.commons.lang3.StringUtils;

import javax.ws.rs.core.GenericType;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Device read request used to read devices and specify pagination.
 */
public class DeviceReadRequest extends ResourcePaginatedReadRequest<Device> {

    private Device.Status status;

    private Device.CustomId cidType;

    private String cid;

    private String sn;

    private String esn;

    private String mac;

    private String imei;

    private String profile;

    private String prop;

    private String propVal;

    private String propOp;

    private Double lat;

    private Double lon;

    private Double radius;

    private String app;

    private String actyStatus;

    private List<String> tags;

    private String zone;

    private String name;

    public Device.Status getStatus() {
        return status;
    }

    public DeviceReadRequest setStatus(Device.Status status) {
        this.status = status;
        return this;
    }

    public Device.CustomId getCidType() {
        return cidType;
    }

    public DeviceReadRequest setCidType(Device.CustomId cidType) {
        this.cidType = cidType;
        return this;
    }

    public String getCid() {
        return cid;
    }

    public DeviceReadRequest setCid(String cid) {
        this.cid = cid;
        return this;
    }

    public String getSn() {
        return sn;
    }

    public DeviceReadRequest setSn(String sn) {
        this.sn = sn;
        return this;
    }

    public String getEsn() {
        return esn;
    }

    public DeviceReadRequest setEsn(String esn) {
        this.esn = esn;
        return this;
    }

    public String getMac() {
        return mac;
    }

    public DeviceReadRequest setMac(String mac) {
        this.mac = mac;
        return this;
    }

    public String getImei() {
        return imei;
    }

    public DeviceReadRequest setImei(String imei) {
        this.imei = imei;
        return this;
    }

    public String getProfile() {
        return profile;
    }

    public DeviceReadRequest setProfile(String profile) {
        this.profile = profile;
        return this;
    }

    public String getProp() {
        return prop;
    }

    public DeviceReadRequest setProp(String prop) {
        this.prop = prop;
        return this;
    }

    public String getPropVal() {
        return propVal;
    }

    public DeviceReadRequest setPropVal(String propVal) {
        this.propVal = propVal;
        return this;
    }

    public String getPropOp() {
        return propOp;
    }

    public DeviceReadRequest setPropOp(String propOp) {
        this.propOp = propOp;
        return this;
    }

    public Double getLat() {
        return lat;
    }

    public DeviceReadRequest setLat(Double lat) {
        this.lat = lat;
        return this;
    }

    public Double getLon() {
        return lon;
    }

    public DeviceReadRequest setLon(Double lon) {
        this.lon = lon;
        return this;
    }

    public Double getRadius() {
        return radius;
    }

    public DeviceReadRequest setRadius(Double radius) {
        this.radius = radius;
        return this;
    }

    public String getApp() {
        return app;
    }

    public DeviceReadRequest setApp(String app) {
        this.app = app;
        return this;
    }

    public String getActyStatus() {
        return actyStatus;
    }

    public DeviceReadRequest setActyStatus(String actyStatus) {
        this.actyStatus = actyStatus;
        return this;
    }

    public List<String> getTags() {
        return tags;
    }

    public DeviceReadRequest setTags(List<String> tags) {
        this.tags = tags;
        return this;
    }

    public String getZone() {
        return zone;
    }

    public DeviceReadRequest setZone(String zone) {
        this.zone = zone;
        return this;
    }

    public String getName() {
        return name;
    }

    public DeviceReadRequest setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    protected Request request() {
        final Map<String, String> filter = new HashMap<>();

        if (status != null) filter.put("status", status.value());
        if (cidType != null) filter.put("cidType", cidType.value());
        if (StringUtils.isNotBlank(cid)) filter.put("cid", cid);
        if (StringUtils.isNotBlank(sn)) filter.put("sn", sn);
        if (StringUtils.isNotBlank(esn)) filter.put("esn", esn);
        if (StringUtils.isNotBlank(mac)) filter.put("mac", mac);
        if (StringUtils.isNotBlank(imei)) filter.put("imei", imei);
        if (StringUtils.isNotBlank(profile)) filter.put("profile", profile);
        if (StringUtils.isNotBlank(prop)) filter.put("prop", prop);
        if (StringUtils.isNotBlank(propVal)) filter.put("propVal", propVal);
        if (StringUtils.isNotBlank(propOp)) filter.put("propOp", propOp);
        if (lat != null) filter.put("lat", String.valueOf(lat));
        if (lon != null) filter.put("lon", String.valueOf(lon));
        if (radius != null) filter.put("radius", String.valueOf(radius));
        if (StringUtils.isNotBlank(app)) filter.put("app", app);
        if (StringUtils.isNotBlank(actyStatus)) filter.put("actyStatus", actyStatus);
        if (tags != null && tags.size() > 0) filter.put("tags", StringUtils.join(tags, ","));
        if (StringUtils.isNotBlank(zone)) filter.put("zone", zone);
        if (StringUtils.isNotBlank(name)) filter.put("name", name);

        filter.putAll(getPaginationParameters());

        return Request.get("devices", filter);
    }

    @Override
    protected RandomAccessResultPage<Device> parseEntity(Response response) {
        return response.readEntity(new GenericType<RandomAccessResultPage<Device>>(){});
    }
}
