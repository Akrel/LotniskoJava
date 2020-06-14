package com.example.mainserverpackage;

import com.example.model.database.Reservation;
import org.springframework.data.repository.CrudRepository;

/**
 * Interfejs pozwalający na tworzenie zapytań na podstawie nazw metod do tabeli Reservation
 */
public interface ReservationRepository extends CrudRepository<Reservation, Integer> {

    public Iterable<Reservation> findByClient_Id(int clientId);
}