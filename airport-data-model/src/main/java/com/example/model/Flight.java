package com.example.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Flight implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private Date data_wylotu;
    private Date date_przylotu;
    private int cena;
    private int ilosc_miejsc;

    public Flight(int fId, int cena, int ilosc_miejsc) {

        this.id = fId;
        this.cena = cena;
        this.ilosc_miejsc = ilosc_miejsc;
    }

    public Flight() {
    }

    ;

    @ManyToOne
    @JoinColumn(name = "destination", referencedColumnName = "code")
    private Airport destination;

    @ManyToOne
    @JoinColumn(name = "origin", referencedColumnName = "code")
    private Airport origin;


    public Date getData_wylotu() {
        return data_wylotu;
    }

    public Date getDate_przylotu() {
        return date_przylotu;
    }


    public Integer getId() {
        return id;
    }

    public void setData_wylotu(Date data_wylotu) {
        this.data_wylotu = data_wylotu;
    }

    public void setDate_przylotu(Date date_przylotu) {
        this.date_przylotu = date_przylotu;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void showData() {

        System.out.println(id);
        System.out.println(cena);

    }

    public int getIlosc_miejsc() {
        return ilosc_miejsc;
    }

    public void setIlosc_miejsc(int ilosc_miejsc) {
        this.ilosc_miejsc = ilosc_miejsc;
    }
}
