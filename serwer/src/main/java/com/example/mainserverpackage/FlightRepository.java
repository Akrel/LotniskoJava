package com.example.mainserverpackage;

import com.example.model.database.Flight;
import org.springframework.data.repository.CrudRepository;

public interface FlightRepository extends CrudRepository<Flight, Integer> {


    public Iterable<Flight> findAllByOrigin(String origin);

    public Iterable<Flight> findByOriginCityAndDestinationCity(String origin, String destination);

    public Iterable<Flight> findByDateArrivalAndAndDateDeparture(String arrival, String departure);


}