package com.connio.sdk.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Connio generic entity response when deleting resources.
 */
public class DeletedResponse {

    private final String deleted;

    @JsonCreator
    public DeletedResponse(@JsonProperty("deleted") String deleted) {
        this.deleted = deleted;
    }

    public String getDeleted() {
        return deleted;
    }
}
