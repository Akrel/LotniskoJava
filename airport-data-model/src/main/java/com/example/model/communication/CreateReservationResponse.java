package com.example.model.communication;

import com.example.model.database.User;

import java.io.Serializable;

public class CreateReservationResponse implements Serializable {

    private String status;




    public CreateReservationResponse(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}