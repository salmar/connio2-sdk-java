package com.connio.sdk.resource.property;

import com.connio.sdk.request.property.PropertyAddRequest;
import com.connio.sdk.request.property.PropertyDeleteRequest;
import com.connio.sdk.request.property.PropertyUpdateRequest;
import com.connio.sdk.resource.Resource;
import com.connio.sdk.resource.deviceprofile.DeviceProfile;
import com.fasterxml.jackson.annotation.*;
import com.google.common.base.MoreObjects;
import org.apache.commons.lang3.text.WordUtils;
import org.joda.time.DateTime;

import java.util.Objects;
import java.util.Optional;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Property extends Resource<PropertyUpdateRequest, PropertyDeleteRequest> {

    public enum Type {
        Enum,
        Number,
        String,
        Waypoint,
        Boolean,
        Object,
        Blob,
        File;

        @JsonValue
        public String value() {
            return name().toLowerCase();
        }

        @JsonCreator
        public static Type fromValue(@JsonProperty("name") final String name) {
            return Type.valueOf(WordUtils.capitalize(name.toLowerCase()));
        }
    }

    public enum Access {
        Protected,
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

    public enum PublishPolicy {
        Never,
        Always,
        Changed;

        @JsonValue
        public String value() {
            return name().toLowerCase();
        }

        @JsonCreator
        public static PublishPolicy fromValue(@JsonProperty("name") final String name) {
            return PublishPolicy.valueOf(WordUtils.capitalize(name));
        }
    }

    private final String id;

    private final String name;

    private final String accountId;

    private final String ownerId;

    private final Type type;

    private final Access access;

    private final PublishPolicy publish;

    private final Measurement measurement;

    private final Boundaries boundaries;

    private final Retention retention;

    private final DateTime dateCreated;

    private final DateTime dateModified;


    public Property(@JsonProperty("id") final String id,
                    @JsonProperty("name") final String name,
                    @JsonProperty("accountId") final String accountId,
                    @JsonProperty("ownerId") final String ownerId,
                    @JsonProperty("ownerPath") final String ownerPath,
                    @JsonProperty("readOnly") final Boolean readOnly,
                    @JsonProperty("type") final Type type,
                    @JsonProperty("access") final Access access,
                    @JsonProperty("publish") final PublishPolicy publish,
                    @JsonProperty("inherited") final Boolean inherited,
                    @JsonProperty("qualifiedName") final String qualifiedName,
                    @JsonProperty("measurement") final Measurement measurement,
                    @JsonProperty("boundaries") final Boundaries boundaries,
                    @JsonProperty("retention") final Retention retention,
                    @JsonProperty("dateCreated") final DateTime dateCreated,
                    @JsonProperty("dateModified") final DateTime dateModified) {

        this.id = id;
        this.name = name;
        this.accountId = accountId;
        this.ownerId = ownerId;
        this.type = type;
        this.access = access;
        this.publish = publish;
        this.measurement = measurement;
        this.boundaries = boundaries;
        this.retention = retention;
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
    }


    public String getId() {
        return id;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    public Access getAccess() {
        return access;
    }

    public PublishPolicy getPublish() {
        return publish;
    }

    public Measurement getMeasurement() {
        return measurement;
    }

    public Optional<Boundaries> getBoundaries() {
        return Optional.ofNullable(boundaries);
    }

    public Retention getRetention() {
        return retention;
    }

    public DateTime getDateCreated() {
        return dateCreated;
    }

    public Optional<DateTime> getDateModified() {
        return Optional.ofNullable(dateModified);
    }

    public static PropertyAddRequest create(DeviceProfile deviceProfile, String name, Type type) {
        return new PropertyAddRequest(deviceProfile, name, type);
    }

    @Override
    public PropertyUpdateRequest update() {
        return new PropertyUpdateRequest(ownerId, id);
    }

    @Override
    public PropertyDeleteRequest delete() {
        return new PropertyDeleteRequest(ownerId, id);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        Property other = (Property) obj;
        return  Objects.equals(this.id, other.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper("Property")
                .add("id", getId().toString())
                .add("name", getName().toString())
                .add("accountId", getAccountId())
                .add("ownerId", getOwnerId())
                .toString();
    }

}

