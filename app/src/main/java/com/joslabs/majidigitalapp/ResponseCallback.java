package com.joslabs.majidigitalapp;

import com.google.gson.annotations.SerializedName;

public class ResponseCallback {
    @SerializedName("message")
    String message;
    @SerializedName("error")
    boolean error;
    @SerializedName("userid")
    String itemId;

    public ResponseCallback(String message, boolean error, String itemId) {
        this.message = message;
        this.error = error;
        this.itemId = itemId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }
}
