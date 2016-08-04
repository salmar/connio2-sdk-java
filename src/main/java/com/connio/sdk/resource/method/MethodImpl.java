package com.connio.sdk.resource.method;

import com.connio.sdk.resource.Context;
import com.fasterxml.jackson.annotation.*;
import com.google.common.base.MoreObjects;
import org.apache.commons.lang3.text.WordUtils;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class MethodImpl {

    public enum ExecType {
        Javascript,
        Lua;

        @JsonValue
        public String value() {
            return name().toLowerCase();
        }

        @JsonCreator
        public static ExecType fromValue(@JsonProperty("name") final String name) {
            return ExecType.valueOf(WordUtils.capitalize(name.toLowerCase()));
        }
    }

    private final Context context;

    private final String funcBody;

    private final ExecType execType;

    @JsonCreator
    public MethodImpl(@JsonProperty("context") Context context,
                      @JsonProperty("funcBody") String funcBody,
                      @JsonProperty("execType") final ExecType execType) {

        this.context = context;
        this.funcBody = funcBody;
        this.execType = execType;
    }

    public Context getContext() {
        return context;
    }

    public String getFuncBody() {
        return funcBody;
    }

    public ExecType getExecType() {
        return execType;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        MethodImpl other = (MethodImpl) obj;
        return Objects.equals(context, other.context) && Objects.equals(funcBody, other.funcBody);
    }

    @Override
    public int hashCode() {
        return Objects.hash(context, funcBody);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper("MethodImpl")
                .add("context", getContext())
                .toString();
    }

    public static class Builder {

        private Context context;

        private final String funcBody;

        private final ExecType execType;

        public Builder(String funcBody, ExecType execType) {
            this.funcBody = funcBody;
            this.execType = execType;
        }

        public Context getContext() {
            return context;
        }

        public Builder setContext(Context context) {
            this.context = context;
            return this;
        }

        public MethodImpl build() {
            return new MethodImpl(context, funcBody, execType);
        }
    }
}
