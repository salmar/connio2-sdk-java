package com.connio.sdk.request.method;

import com.connio.sdk.request.ResourceUpdateRequest;
import com.connio.sdk.http.Request;
import com.connio.sdk.http.Response;
import com.connio.sdk.resource.deviceprofile.DeviceProfile;
import com.connio.sdk.resource.method.Method;
import com.connio.sdk.resource.method.MethodImpl;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MethodUpdateRequest extends ResourceUpdateRequest<Method> {

    private final String deviceProfileId;

    private final String methodId;

    private String name;

    private MethodImpl methodImpl;

    private Long inputPropTTL;

    private String inputId;

    private String outputId;

    public MethodUpdateRequest(String deviceProfileId, String methodId) {
        this.deviceProfileId = deviceProfileId;
        this.methodId = methodId;

        if (deviceProfileId == null || !deviceProfileId.startsWith("_dpf_"))
            throw new IllegalArgumentException("Invalid device profile");
    }

    public MethodUpdateRequest(DeviceProfile deviceProfile, String methodId) {
        this.deviceProfileId = deviceProfile.getId();
        this.methodId = methodId;
    }

    public String getName() {
        return name;
    }

    public MethodUpdateRequest setName(String name) {
        this.name = name;
        return this;
    }

    public MethodImpl getMethodImpl() {
        return methodImpl;
    }

    public MethodUpdateRequest setMethodImpl(MethodImpl methodImpl) {
        this.methodImpl = methodImpl;
        return this;
    }

    public Long getInputPropTTL() {
        return inputPropTTL;
    }

    public MethodUpdateRequest setInputPropTTL(Long inputPropTTL) {
        this.inputPropTTL = inputPropTTL;
        return this;
    }

    public String getInputId() {
        return inputId;
    }

    public MethodUpdateRequest setInputId(String inputId) {
        this.inputId = inputId;
        return this;
    }

    public String getOutputId() {
        return outputId;
    }

    public MethodUpdateRequest setOutputId(String outputId) {
        this.outputId = outputId;
        return this;
    }

    @Override
    protected Request request() {
        final String path = "deviceprofiles/" + deviceProfileId + "/methods/" + methodId;
        return Request.put(path, this);
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
        MethodUpdateRequest that = (MethodUpdateRequest) o;
        return Objects.equals(deviceProfileId, that.deviceProfileId) &&
                Objects.equals(methodId, that.methodId) &&
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(getMethodImpl(), that.getMethodImpl()) &&
                Objects.equals(getInputPropTTL(), that.getInputPropTTL()) &&
                Objects.equals(getInputId(), that.getInputId()) &&
                Objects.equals(getOutputId(), that.getOutputId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(deviceProfileId, methodId, getName(), getMethodImpl(), getInputPropTTL(), getInputId(), getOutputId());
    }
}


