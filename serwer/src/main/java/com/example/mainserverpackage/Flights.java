package com.example.mainserverpackage;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Flights  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private Date data_wylotu;
    private Date date_przylotu;
    private int cena;

    public Flights(int fId,int cena) {

        this.id = fId;
        this.cena = cena;
    }

    public Flights() {
    }

    ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "destination")
    private Airport destination;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orgin")


    private Airport orgin;


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

}
