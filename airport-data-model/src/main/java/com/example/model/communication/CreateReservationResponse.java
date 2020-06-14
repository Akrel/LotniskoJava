package com.example.model.communication;

import java.io.Serializable;
/**
 * Klasa jest modelem komunikacji pomiędzy serwerem a klientem,
 * klasa jest odpowiedzią na żądanie klienta przechowuje infromacje pobrane z bazy dotyczące rezerwacji
 */
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