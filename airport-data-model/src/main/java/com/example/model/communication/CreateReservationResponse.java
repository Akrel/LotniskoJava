package com.example.model.communication;

public class CreateReservationResponse {

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