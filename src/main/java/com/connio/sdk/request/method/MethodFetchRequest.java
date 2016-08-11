package com.connio.sdk.request.method;

import com.connio.sdk.request.ResourceFetchRequest;
import com.connio.sdk.http.Request;
import com.connio.sdk.http.Response;
import com.connio.sdk.resource.deviceprofile.DeviceProfile;
import com.connio.sdk.resource.method.Method;
/**
 * Method fetch request used to fetch a method.
 */
public class MethodFetchRequest extends ResourceFetchRequest<Method> {

    private final String deviceProfileId;

    private final String methodId;

    public MethodFetchRequest(DeviceProfile deviceProfile, String methodId) {
        this.deviceProfileId = deviceProfile.getId();
        this.methodId = methodId;
    }

    @Override
    protected Request request() {
        final String path = "deviceprofiles/" + deviceProfileId + "/methods/" + methodId;
        return Request.get(path);
    }

    @Override
    protected Method parseResourceEntity(Response response) {
        return response.readEntity(Method.class);
    }
}