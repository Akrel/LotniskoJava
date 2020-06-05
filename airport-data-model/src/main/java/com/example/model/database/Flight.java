package com.example.model.database;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Flight implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private Date date_departure;
    private Date date_arrival;
    private int price;
    private int number_seats;

    public Flight( int price, int number_seats) {
        this.price = price;
        this.number_seats = number_seats;
    }


    @ManyToOne
    @JoinColumn(name = "destination", referencedColumnName = "code")
    private Airport destination;

    @ManyToOne
    @JoinColumn(name = "origin", referencedColumnName = "code")
    private Airport origin;
    Flight(){};

    public Date getDate_departure() {
        return date_departure;
    }

    public Date getDate_arrival() {
        return date_arrival;
    }


    public Integer getId() {
        return id;
    }

    public void setDate_departure(Date data_wylotu) {
        this.date_departure = data_wylotu;
    }

    public void setDate_arrival(Date date_przylotu) {
        this.date_arrival = date_przylotu;
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

    public int getNumber_seats() {
        return number_seats;
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

    public void setNumber_seats(int number_seats) {
        this.number_seats = number_seats;
    }

    public int getPrice() {
        return price;
    }
}
