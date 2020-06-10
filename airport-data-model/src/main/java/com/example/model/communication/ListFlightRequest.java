package com.example.model.communication;

import java.io.Serializable;
import java.util.Date;

public class ListFlightRequest implements Serializable {
    private String origin;
    private String destination;
    private Date departureDate;
    private Date arrivalDate;

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }


    public ListFlightRequest() {
    }

    ;

}
