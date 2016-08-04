package com.connio.sdk.resource.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class InfoHeader {

    private final String id;

    private final String qname;

    public InfoHeader(@JsonProperty("id") final String id,
                      @JsonProperty("qname") final String qname) {
        this.id = id;
        this.qname = qname;
    }

    public String getId() {
        return id;
    }

    public String getQname() {
        return qname;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        InfoHeader other = (InfoHeader) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper("InfoHeader")
                .add("id", getId().toString())
                .add("Qualified name", getQname().toString())
                .toString();
    }

}