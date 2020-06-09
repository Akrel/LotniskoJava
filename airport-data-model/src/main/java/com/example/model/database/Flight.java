package com.example.model.database;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

    // alt + insert, generate getters and setters

    @ManyToOne
    @JoinColumn(name = "destination", referencedColumnName = "code")
    private Airport destination;

    @ManyToOne
    @JoinColumn(name = "origin", referencedColumnName = "code")
    private Airport origin;

    public Flight() {
    }

    public Flight(int price, int numberSeats) {
        this.price = price;
        this.numberSeats = numberSeats;
    }

    public Date getDateDepearture() {
        return dateDeparture;
    }

    public Date getDateArrival() {
        return dateArrival;
    }

    public Integer getId() {
        return id;
    }

    public void setDateDepearture(Date data_wylotu) {
        this.dateDeparture = data_wylotu;
    }

    public void setDateArrival(Date date_przylotu) {
        this.dateArrival = date_przylotu;
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
