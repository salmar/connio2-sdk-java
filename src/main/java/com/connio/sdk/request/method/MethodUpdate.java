package com.connio.sdk.request.method;

import com.connio.sdk.request.ResourceUpdateRequest;
import com.connio.sdk.http.Request;
import com.connio.sdk.http.Response;
import com.connio.sdk.resource.method.Method;
import com.connio.sdk.resource.method.MethodImpl;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MethodUpdate extends ResourceUpdateRequest<Method> {

    private final String deviceProfileId;

    private final String methodId;

    private String name;

    private MethodImpl methodImpl;

    private Long inputPropTTL;

    private String inputId;

    private String outputId;


    public MethodUpdate(String deviceProfileId, String methodId) {
        this.deviceProfileId = deviceProfileId;
        this.methodId = methodId;
    }

    public String getName() {
        return name;
    }

    public MethodUpdate setName(String name) {
        this.name = name;
        return this;
    }

    public MethodImpl getMethodImpl() {
        return methodImpl;
    }

    public MethodUpdate setMethodImpl(MethodImpl methodImpl) {
        this.methodImpl = methodImpl;
        return this;
    }

    public Long getInputPropTTL() {
        return inputPropTTL;
    }

    public MethodUpdate setInputPropTTL(Long inputPropTTL) {
        this.inputPropTTL = inputPropTTL;
        return this;
    }

    public String getInputId() {
        return inputId;
    }

    public MethodUpdate setInputId(String inputId) {
        this.inputId = inputId;
        return this;
    }

    public String getOutputId() {
        return outputId;
    }

    public MethodUpdate setOutputId(String outputId) {
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
}


