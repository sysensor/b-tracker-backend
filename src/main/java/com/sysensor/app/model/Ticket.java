package com.sysensor.app.model;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class Ticket extends UUIDBaseEntity {

    @NotNull
    private String price;
    @NotNull
    private String start;
    @NotNull
    private String destination;
    @NotNull
    private String status;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
