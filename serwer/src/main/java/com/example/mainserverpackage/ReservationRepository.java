package com.example.mainserverpackage;

import com.example.model.database.Reservation;
import org.springframework.data.repository.CrudRepository;

public interface ReservationRepository extends CrudRepository<Reservation, Integer> {

    public Iterable<Reservation> findByClient_Id(int clientId);
}