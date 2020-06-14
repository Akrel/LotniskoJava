package com.example.model.communication;

import com.example.model.database.Reservation;

import java.io.Serializable;
import java.util.ArrayList;
/**
 * Klasa jest modelem komunikacji pomiędzy serwerem a klientem,
 * klasa jest odpowiedzią na żądanie klienta przechowuje infromacje pobrane z bazy dotyczące znalezionych rezerwacji
 */
public class FindReservationResponse implements Serializable {
    private String staus;
    private ArrayList<Reservation> listOfReservation;

    public ArrayList<Reservation> getListOfReservation() {
        return listOfReservation;
    }

    public void setListOfReservation(ArrayList<Reservation> listOfReservation) {
        this.listOfReservation = listOfReservation;
    }


    public FindReservationResponse(String staus, ArrayList<Reservation> listOfFlights) {
        this.staus = staus;
        this.listOfReservation = listOfFlights;
    }

    public String getStaus() {
        return staus;
    }

    public void setStaus(String staus) {
        this.staus = staus;
    }


}
