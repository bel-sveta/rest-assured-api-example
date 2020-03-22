package org.bel.apiHelper;

import com.google.gson.annotations.SerializedName;

public enum StatusEnumPet {
    @SerializedName("available")
    available("available"),
    @SerializedName("pending")
    pending("pending"),
    @SerializedName("sold")
    sold("sold");

    private String value;

    StatusEnumPet(String value) {
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
