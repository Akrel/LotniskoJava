package com.example.model.database;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Flight implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private Date dateDeparture;
    private Date dateArrival;
    private int price;
    private int numberSeats;

    public Flight(int price, int numberSeats) {
        this.price = price;
        this.numberSeats = numberSeats;
    }


    @ManyToOne
    @JoinColumn(name = "destination", referencedColumnName = "code")
    private Airport destination;

    @ManyToOne
    @JoinColumn(name = "origin", referencedColumnName = "code")
    private Airport origin;

    Flight() {
    }

    ;

    public Date getDateDepearture() {
        return dateDeparture;
    }

    public Date getDateArrival() {
        return dateArrival;
    }


    public Integer getId() {
        return id;
    }

    public void setDateDepearture(Date dateDeparture) {
        this.dateDeparture = dateDeparture;
    }

    public void setDateArrival(Date dateArrival) {
        this.dateArrival = dateArrival;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void showData() {

        System.out.println(id);
        System.out.println(price);

    }

    public Airport getOrigin() {
        return origin;
    }

    public int getNumberSeats() {
        return numberSeats;
    }

    public Airport getDestination() {
        return destination;
    }

    public void setOrigin(Airport origin) {
        this.origin = origin;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setNumberSeats(int number_seats) {
        this.numberSeats = number_seats;
    }

    public int getPrice() {
        return price;
    }


}
