package com.sysensor.app.model;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class BusRoute extends UUIDBaseEntity {

    @NotNull
    private String name;

    @NotNull
    private String start;

    @NotNull
    private String destination;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = false, fetch = FetchType.LAZY,
            mappedBy = "busRoute"
    )
    private List<Bus> busList = new ArrayList<>();

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = false, fetch = FetchType.LAZY,
            mappedBy = "busRoute"
    )
    private List<TimeKeeper> timeKeeperList = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<Bus> getBusList() {
        return busList;
    }

    public void setBusList(List<Bus> busList) {
        this.busList = busList;
    }

    public List<TimeKeeper> getTimeKeeperList() {
        return timeKeeperList;
    }

    public void setTimeKeeperList(List<TimeKeeper> timeKeeperList) {
        this.timeKeeperList = timeKeeperList;
    }
}
