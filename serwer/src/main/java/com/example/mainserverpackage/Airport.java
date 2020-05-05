package com.example.mainserverpackage;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Airport {
    @Id
    @Column(name = "KOD_LOTN", nullable = false)
    private String kod_lotniska;
    private String kraj;
    private String miasto;

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


    public String getMiasto() {
        return miasto;
    }


    public void setKraj(String kraj) {
        this.kraj = kraj;
    }
}
