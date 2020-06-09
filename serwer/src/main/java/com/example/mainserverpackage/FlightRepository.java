package com.example.mainserverpackage;

import java.util.Date;

import com.example.model.database.Flight;
import org.springframework.data.repository.CrudRepository;

public interface FlightRepository extends CrudRepository<Flight, Integer> {

    public Iterable<Flight> findAllByOrigin(String origin);
    public Iterable<Flight> findAllByDestination(String destination);

    public Iterable<Flight> findAllByOriginAndDestination(String origin, String destination);


    

}