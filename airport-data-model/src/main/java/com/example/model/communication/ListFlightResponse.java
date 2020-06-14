package com.example.model.communication;

import com.example.model.database.Flight;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 * Klasa jest modelem komunikacji pomiędzy serwerem a klientem,
 * klasa jest odpowiedzią na żądanie klienta przechowuje infromacje pobrane z bazy dotyczące lotów
 */
public class ListFlightResponse<F> implements Serializable {

    private ArrayList<Flight> listOfFlights;

    public ListFlightResponse() {
        listOfFlights = new ArrayList<Flight>();
    }

    public List<Flight> getListOfFlights() {
        return listOfFlights;
    }

    public void insertToList(Flight obiekt) {
        listOfFlights.add(obiekt);
    }

    public List<Flight> getListOfFlight() {
        return this.listOfFlights;
    }

    ;

}
