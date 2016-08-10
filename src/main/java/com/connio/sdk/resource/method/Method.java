package com.connio.sdk.resource.method;

import com.connio.sdk.request.method.MethodCreateRequest;
import com.connio.sdk.request.method.MethodDeleteRequest;
import com.connio.sdk.request.method.MethodUpdateRequest;
import com.connio.sdk.resource.Resource;
import com.connio.sdk.resource.deviceprofile.DeviceProfile;
import com.fasterxml.jackson.annotation.*;
import com.google.common.base.MoreObjects;
import org.apache.commons.lang3.text.WordUtils;
import org.joda.time.DateTime;

import java.util.Objects;
import java.util.Optional;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Method extends Resource<MethodUpdateRequest, MethodDeleteRequest> {

    public enum Access {
        Private,
        Public;

        @JsonValue
        public String value() {
            return name().toLowerCase();
        }
        @JsonCreator
        public static Access fromValue(@JsonProperty("name") final String name) {
            return Access.valueOf(WordUtils.capitalize(name.toLowerCase()));
        }
    }

    private final String id;

    private final String name;

    private final String accountId;

    private final String ownerId;

    private final String ownerPath;

    private final Access access;

    private final Boolean inherited;

    private final String qualifiedName;

    private final MethodImpl methodImpl;

    private final Long inputPropTTL;

    private final String inputId;

    private final String outputId;

    private final DateTime dateCreated;

    private final DateTime dateModified;


    public Method(@JsonProperty("id") final String id,
                  @JsonProperty("name") final String name,
                  @JsonProperty("accountId") final String accountId,
                  @JsonProperty("ownerId") final String ownerId,
                  @JsonProperty("ownerPath") final String ownerPath,
                  @JsonProperty("access") final Access access,
                  @JsonProperty("inherited") final Boolean inherited,
                  @JsonProperty("qualifiedName") final String qualifiedName,
                  @JsonProperty("methodImpl") final MethodImpl methodImpl,
                  @JsonProperty("inputPropTTL") final Long inputPropTTL,
                  @JsonProperty("inputId") final String inputId,
                  @JsonProperty("outputId") final String outputId,
                  @JsonProperty("dateCreated") final DateTime dateCreated,
                  @JsonProperty("dateModified") final DateTime dateModified) {

        this.id = id;
        this.name = name;
        this.accountId = accountId;
        this.ownerId = ownerId;
        this.ownerPath = ownerPath;
        this.access = access;
        this.inherited = inherited;
        this.qualifiedName = qualifiedName;
        this.methodImpl = methodImpl;
        this.inputPropTTL = inputPropTTL;
        this.inputId = inputId;
        this.outputId = outputId;
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public String getOwnerPath() {
        return ownerPath;
    }

    public Access getAccess() {
        return access;
    }

    public Boolean getInherited() {
        return inherited;
    }

    public String getQualifiedName() {
        return qualifiedName;
    }

    public MethodImpl getMethodImpl() {
        return methodImpl;
    }

    public Optional<Long> getInputPropTTL() {
        return Optional.ofNullable(inputPropTTL);
    }

    public Optional<String> getInputId() {
        return Optional.ofNullable(inputId);
    }

    public Optional<String> getOutputId() {
        return Optional.ofNullable(outputId);
    }

    public DateTime getDateCreated() {
        return dateCreated;
    }

    public Optional<DateTime> getDateModified() {
        return Optional.ofNullable(dateModified);
    }

    public static MethodCreateRequest create(DeviceProfile deviceProfile, String name, Access access, MethodImpl impl) {
        return new MethodCreateRequest(deviceProfile, name, access, impl);
    }

    @Override
    public MethodUpdateRequest update() {
        return new MethodUpdateRequest(ownerId, id);
    }

    @Override
    public MethodDeleteRequest delete() {
        return new MethodDeleteRequest(ownerId, id);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        Method other = (Method) obj;
        return  Objects.equals(this.getId(), other.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper("Method")
                .add("id", getId())
                .add("name", getName())
                .add("accountId", getAccountId())
                .add("ownerId", getOwnerId())
                .toString();
    }

}
