package com.sysensor.app.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Bus extends UUIDBaseEntity {

    @NotNull
    private String registration_no;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bus_owner_uuid")
    @NotNull
    private BusOwner busOwner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bus_route_uuid")
    private BusRoute busRoute;

    public String getRegistration_no() {
        return registration_no;
    }

    public void setRegistration_no(String registration_no) {
        this.registration_no = registration_no;
    }

    public BusOwner getBusOwner() {
        return busOwner;
    }

    public void setBusOwner(BusOwner busOwner) {
        this.busOwner = busOwner;
    }

    public BusRoute getBusRoute() {
        return busRoute;
    }

    public void setBusRoute(BusRoute busRoute) {
        this.busRoute = busRoute;
    }
}
