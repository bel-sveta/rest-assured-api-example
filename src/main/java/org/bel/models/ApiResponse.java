package org.bel.models;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class ApiResponse {

    private Integer code;
    private String type;
    private String message;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("code", code).append("type", type).append("message", message).toString();
    }
}
