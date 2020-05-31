package com.example.model;

import java.io.Serializable;
import java.util.ArrayList;

public class ListFlightRequest implements Serializable {

    private final ArrayList<Object> listOfFlights;

    public ListFlightRequest()
    {
        listOfFlights = new ArrayList<Object>();
    }
}
