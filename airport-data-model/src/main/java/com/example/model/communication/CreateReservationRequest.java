package com.example.model.communication;


import com.example.model.database.User;

import java.io.Serializable;
/**
 * Klasa jest modelem komunikacji pomiędzy serwerem a klientem,
 * klasa jest żądaniem od klienta do serwera informacji dotyczących tworzenia rezerwacji.
 */
public class CreateReservationRequest implements Serializable {
    private User user;
    private int flightId;
    private String passengerName;
    private String passengerSurname;

    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public String getPassengerSurname() {
        return passengerSurname;
    }

    public void setPassengerSurname(String passengerSurname) {
        this.passengerSurname = passengerSurname;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}