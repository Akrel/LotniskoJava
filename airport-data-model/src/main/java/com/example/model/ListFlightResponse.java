package com.example.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ListFlightResponse implements Serializable {

    private ArrayList<Flight> listOfFlights;

    public ListFlightResponse()
    {
        listOfFlights = new ArrayList<>();
    }

    public List<Flight> getListOfFlights() {
        return listOfFlights;
    }




    public void insetToList(Flight obiekt)
    {
        listOfFlights.add(obiekt);
    }

    public Flight returnAirport()
    {
        Flight test = this.listOfFlights.get(0);
        return test;

    }
}
