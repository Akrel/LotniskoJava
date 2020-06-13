package com.example.mainserverpackage;

import com.example.model.database.Airport;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface AirportRepository extends CrudRepository<Airport, Integer> {

}
