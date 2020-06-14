package com.example.model.database;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.Set;

/**
 * Klasa lotniska przechowuje informacje o lotnisku
 * Klasa jest mapowana z modelu obiektowego na obiekt realcyjny
 */
@Entity
public class Airport implements Serializable {

    @Id
    private String code;

    private String country;

    private String city;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "destination")
    private Set<Flight> flightsTo;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "origin")
    private Set<Flight> flightsFrom;

    public Airport() {
    }


    public Airport(String cod_ap, String country, String city) {
        this.code = cod_ap;
        this.country = country;
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public String getCode() {
        return code;
    }

    public String getCity() {
        return city;
    }

    public Set<Flight> getFlightsFrom() {
        return flightsFrom;
    }

    public Set<Flight> getFlightsTo() {
        return flightsTo;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setFlightsFrom(Set<Flight> flightsFrom) {
        this.flightsFrom = flightsFrom;
    }

    public void setFlightsTo(Set<Flight> flightsTo) {
        this.flightsTo = flightsTo;
    }
}
