package com.example.mainserverpackage;

import javax.persistence.*;
import java.util.Set;
import java.io.Serializable;

@Entity
public class Airport implements Serializable {
    @Id
    @Column(name = "KOD_LOTN", nullable = false)
    private String kod_lotniska;
    private String kraj;
    private String miasto;

    public Airport() {
    }


    public Airport(String cod_ap, String country, String city) {
        this.kod_lotniska = cod_ap;
        this.kraj = country;
        this.miasto = city;
    }


    @OneToMany(mappedBy = "destination")
    private Set<Flights> flights;
    @OneToMany(mappedBy = "orgin")
    private Set<Flights> flightsOrg;

    public String getKod_lotniska() {
        return kod_lotniska;
    }

    public void setKod_lotniska(String kod_lotniska) {
        this.kod_lotniska = kod_lotniska;
    }

    public String getKraj() {
        return kraj;
    }

    public void setMiasto(String miasto) {
        this.miasto = miasto;
    }

    public void showDetalis() {
        System.out.println(this.kod_lotniska);
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
