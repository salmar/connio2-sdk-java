package com.connio.sdk.resource.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import org.joda.time.DateTime;

import java.util.Objects;
import java.util.Optional;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class AlertValue {

    private final DateTime lastTriggered;

    private final Long count;

    private final AlertIncident incident;

    public AlertValue(@JsonProperty("lastTriggered") final DateTime lastTriggered,
                      @JsonProperty("count") final Long count,
                      @JsonProperty("incident") final AlertIncident incident) {

        this.lastTriggered = lastTriggered;
        this.count = count;
        this.incident = incident;
    }

    public DateTime getLastTriggered() {
        return lastTriggered;
    }

    public Long getCount() {
        return count;
    }

    public Optional<AlertIncident> getIncident() { return Optional.ofNullable(incident); }


    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        AlertValue other = (AlertValue) obj;
        return Objects.equals(this.lastTriggered, other.lastTriggered) && Objects.equals(this.count, other.count) &&
                Objects.equals(this.incident, other.incident);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lastTriggered, count, incident);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper("AlertValue")
                .add("lastTriggered", lastTriggered)
                .add("count", count)
                .add("incident", incident)
                .toString();
    }
}
