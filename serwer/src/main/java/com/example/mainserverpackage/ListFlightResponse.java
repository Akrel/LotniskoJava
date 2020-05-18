package com.example.mainserverpackage;

import java.io.Serializable;
import java.util.*;

public class ListFlightResponse implements Serializable {

    private ArrayList<Flights> listOfFlights;

    public ListFlightResponse()
    {
        listOfFlights = new ArrayList<>();
    }

    public List<Flights> getListOfFlights() {
        return listOfFlights;
    }




    public void insetToList(Flights obiekt)
    {
        listOfFlights.add(obiekt);
    }

    public Flights returnAirport()
    {
        Flights test = this.listOfFlights.get(0);
        return test;

    }
}
