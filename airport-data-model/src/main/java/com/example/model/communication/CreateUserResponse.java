package com.example.model.communication;

import java.io.Serializable;

public class CreateUserResponse implements Serializable {

    String status;

    public CreateUserResponse(String status) {
        this.status = status;
    }

    public CreateUserResponse() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
