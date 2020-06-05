package com.example.model.database;


import javax.persistence.*;
import java.io.Serializable;
@Entity
public class Reservation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String passengerName;
    private String passengerSurname;
    @ManyToOne
    @JoinColumn(name = "flightId",referencedColumnName = "id")
    private Flight flightId;


}
