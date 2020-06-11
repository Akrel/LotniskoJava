package com.example.model.communication;

import com.example.model.database.User;

import java.io.Serializable;

public class LoginUserResponse implements Serializable {

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LoginUserResponse(String status, User user) {
        this.status = status;
        this.user = user;
    }

    private String status;

    private User user;

}