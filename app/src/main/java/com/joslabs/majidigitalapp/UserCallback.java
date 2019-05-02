package com.joslabs.majidigitalapp;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserCallback {
    @SerializedName("message")
    String message;
    @SerializedName("error")
    boolean error;
    @SerializedName("users")
    List<UserModel> users;

    public UserCallback(String message, boolean error, List<UserModel> users) {
        this.message = message;
        this.error = error;
        this.users = users;
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

    public List<UserModel> getUsers() {
        return users;
    }

    public void setUsers(List<UserModel> users) {
        this.users = users;
    }
}
