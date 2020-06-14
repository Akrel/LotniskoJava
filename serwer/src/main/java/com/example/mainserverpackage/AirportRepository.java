package com.example.mainserverpackage;

import com.example.model.database.Airport;
import org.springframework.data.repository.CrudRepository;

/**
 * Interfejs pozwalający na tworzenie zapytań na podstawie nazw metod do tabeli Airport
 */
public interface AirportRepository extends CrudRepository<Airport, Integer> {

}
