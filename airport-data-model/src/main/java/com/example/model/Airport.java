package com.example.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.Set;

@Entity
public class Airport implements Serializable {

    @Id
    private String code;

    private String kraj;

    private String miasto;

    @OneToMany(mappedBy = "destination")
    private Set<Flight> flightsTo;

    @OneToMany(mappedBy = "origin")
    private Set<Flight> flightsFrom;

    public Airport() {
    }


    public Airport(String cod_ap, String country, String city) {
        this.code = cod_ap;
        this.kraj = country;
        this.miasto = city;
    }




    public String getKod_lotniska() {
        return code;
    }

    public void setKod_lotniska(String kod_lotniska) {
        this.code = kod_lotniska;
    }

    public String getKraj() {
        return kraj;
    }

    public void setMiasto(String miasto) {
        this.miasto = miasto;
    }

    public void showDetalis() {
        System.out.println(this.code);
        System.out.println(this.kraj);
        System.out.println(this.miasto);
    }

    public String getMiasto() {
        return miasto;
    }


    public void setKraj(String kraj) {
        this.kraj = kraj;
    }
}
