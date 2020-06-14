package com.example.mainserverpackage;

import com.example.model.database.Flight;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;

/**
 * Interfejs pozwalający na tworzenie zapytań na podstawie nazw metod dla tabeli Usera
 */

public interface FlightRepository extends CrudRepository<Flight, Integer> {


    public Iterable<Flight> findAllByOrigin(String origin);

    public Iterable<Flight> findByOriginCityAndDestinationCity(String origin, String destination);

    public Iterable<Flight> findByDateArrivalAndAndDateDeparture(String arrival, String departure);

    @Query(value = "select f from Flight f where f.dateDeparture >= :odDate and f.dateDeparture <= :doDate AND f.origin.city = :origin AND f.destination.city = :desti")
    public Iterable<Flight> findFlightSearchingFlight(@Param("odDate")Date odDate,@Param("doDate") Date doDate,@Param("origin")String origin,@Param("desti")String desti);
}