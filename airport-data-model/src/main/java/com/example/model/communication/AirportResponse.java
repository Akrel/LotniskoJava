package com.example.model.communication;

import com.example.model.database.Airport;

import java.io.Serializable;
import java.util.ArrayList;

public class AirportResponse implements Serializable {
    public String getStatus() {
        return status;
    }

    private String status;
    private Iterable<Airport> listOfAirport;

    public Iterable<Airport> getListOfAirport() {
        return listOfAirport;
    }

    public void setListOfAirport(ArrayList<Airport> listOfAirport) {
        this.listOfAirport = listOfAirport;
    }



    public AirportResponse(String status, Iterable<Airport> airports) {
        this.status = status;
        this.listOfAirport = airports;
    }
}
