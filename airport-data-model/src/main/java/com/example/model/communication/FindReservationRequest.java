package com.example.model.communication;

import java.io.Serializable;

public class FindReservationRequest implements Serializable {

    private int userId;
    private String status;

    public FindReservationRequest(int userId, String status) {
        this.userId = userId;
        this.status = status;
    }

    public FindReservationRequest(int id) {
        this.userId = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
