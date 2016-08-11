package com.connio.sdk.request.method;

import com.connio.sdk.http.Request;
import com.connio.sdk.http.Response;
import com.connio.sdk.request.ResourceCreateRequest;
import com.connio.sdk.resource.deviceprofile.DeviceProfile;
import com.connio.sdk.resource.method.Method;
import com.connio.sdk.resource.method.MethodImpl;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Objects;

/**
 * Method create request used to create new methods.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MethodCreateRequest extends ResourceCreateRequest<Method> {

    private final String deviceProfileId;

    private final String name;

    private final Method.Access access;

    private final MethodImpl methodImpl;

    private Long inputPropTTL;

    private String inputId;

    private String outputId;

    public MethodCreateRequest(DeviceProfile deviceProfile, String name, Method.Access access, MethodImpl methodImpl) {
        this.deviceProfileId = deviceProfile.getId();
        this.name = name;
        this.access = access;
        this.methodImpl = methodImpl;
    }

    public String getDeviceProfileId() {
        return deviceProfileId;
    }

    public String getName() {
        return name;
    }

    public Method.Access getAccess() {
        return access;
    }

    public MethodImpl getMethodImpl() {
        return methodImpl;
    }

    public Long getInputPropTTL() {
        return inputPropTTL;
    }

    public MethodCreateRequest setInputPropTTL(Long inputPropTTL) {
        this.inputPropTTL = inputPropTTL;
        return this;
    }

    public String getInputId() {
        return inputId;
    }

    public MethodCreateRequest setInputId(String inputId) {
        this.inputId = inputId;
        return this;
    }

    public String getOutputId() {
        return outputId;
    }

    public MethodCreateRequest setOutputId(String outputId) {
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
        MethodCreateRequest that = (MethodCreateRequest) o;
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
