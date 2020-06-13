package com.example.mainserverpackage;

import com.example.model.database.Airport;
import org.springframework.data.repository.CrudRepository;

public interface AirportRepository extends CrudRepository<Airport, Integer> {

}
