package com.joslabs.majidigitalapp;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MpesaCallback {
    @SerializedName("message")
    String message;
    @SerializedName("error")
    boolean error;
    @SerializedName("mpesa")
    List<MpesaModel> mpesaModelList;

    public MpesaCallback() {
    }

    public MpesaCallback(String message, boolean error, List<MpesaModel> mpesaModelList) {
        this.message = message;
        this.error = error;
        this.mpesaModelList = mpesaModelList;
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

    public List<MpesaModel> getMpesaModelList() {
        return mpesaModelList;
    }

    public void setMpesaModelList(List<MpesaModel> mpesaModelList) {
        this.mpesaModelList = mpesaModelList;
    }
}
