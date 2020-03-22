package org.bel.apiHelper;

import com.google.gson.annotations.SerializedName;

public enum StatusEnumOrder {
    @SerializedName("placed")
    placed("placed"),

    @SerializedName("approved")
    approved("approved"),

    @SerializedName("delivered")
    delivered("delivered");

    private String value;

    StatusEnumOrder(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

}
