package com.sysensor.app.model;

import javax.persistence.*;

@Entity
public class TimeKeeper extends UUIDBaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bus_route_uuid")
    private BusRoute busRoute;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_uuid", nullable = false)
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BusRoute getBusRoute() {
        return busRoute;
    }

    public void setBusRoute(BusRoute busRoute) {
        this.busRoute = busRoute;
    }

}
