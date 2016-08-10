package com.connio.sdk.request.method;

import com.connio.sdk.request.ResourceAddRequest;
import com.connio.sdk.http.Request;
import com.connio.sdk.http.Response;
import com.connio.sdk.resource.deviceprofile.DeviceProfile;
import com.connio.sdk.resource.method.Method;
import com.connio.sdk.resource.method.MethodImpl;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MethodAddRequest extends ResourceAddRequest<Method> {

    private String deviceProfileId;

    private String name;

    private Method.Access access;

    private MethodImpl methodImpl;

    private Long inputPropTTL;

    private String inputId;

    private String outputId;

    public MethodAddRequest(DeviceProfile deviceProfile, String name, Method.Access access, MethodImpl methodImpl) {
        this.deviceProfileId = deviceProfile.getId();
        this.name = name;
        this.access = access;
        this.methodImpl = methodImpl;
    }

    public String getDeviceProfileId() {
        return deviceProfileId;
    }

    public MethodAddRequest setDeviceProfileId(String deviceProfileId) {
        this.deviceProfileId = deviceProfileId;
        return this;
    }

    public String getName() {
        return name;
    }

    public MethodAddRequest setName(String name) {
        this.name = name;
        return this;
    }

    public Method.Access getAccess() {
        return access;
    }

    public MethodAddRequest setAccess(Method.Access access) {
        this.access = access;
        return this;
    }

    public MethodImpl getMethodImpl() {
        return methodImpl;
    }

    public MethodAddRequest setMethodImpl(MethodImpl methodImpl) {
        this.methodImpl = methodImpl;
        return this;
    }

    public Long getInputPropTTL() {
        return inputPropTTL;
    }

    public MethodAddRequest setInputPropTTL(Long inputPropTTL) {
        this.inputPropTTL = inputPropTTL;
        return this;
    }

    public String getInputId() {
        return inputId;
    }

    public MethodAddRequest setInputId(String inputId) {
        this.inputId = inputId;
        return this;
    }

    public String getOutputId() {
        return outputId;
    }

    public MethodAddRequest setOutputId(String outputId) {
        this.outputId = outputId;
        return this;
    }

    @Override
    @JsonIgnore
    protected Request request() {
        final String path = "deviceprofiles/" + deviceProfileId + "/methods";
        return Request.post(path, this);
    }

    @Override
    @JsonIgnore
    protected Method parseEntity(Response response) {
        return response.readEntity(Method.class);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MethodAddRequest that = (MethodAddRequest) o;
        return Objects.equals(getDeviceProfileId(), that.getDeviceProfileId()) &&
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(getAccess(), that.getAccess()) &&
                Objects.equals(getMethodImpl(), that.getMethodImpl()) &&
                Objects.equals(getInputPropTTL(), that.getInputPropTTL()) &&
                Objects.equals(getInputId(), that.getInputId()) &&
                Objects.equals(getOutputId(), that.getOutputId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDeviceProfileId(), getName(), getAccess(), getMethodImpl(), getInputPropTTL(),
                getInputId(), getOutputId());
    }
}
