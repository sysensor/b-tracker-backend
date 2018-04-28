package com.sysensor.app.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Bus {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "uuid")
    private String uuid;

    @NotNull
    private String registration_no;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bus_owner_uuid")
    @NotNull
    private BusOwner busOwner;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

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
}
