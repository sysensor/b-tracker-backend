package com.sysensor.app.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class BusOwner extends UUIDBaseEntity {

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true, fetch = FetchType.LAZY,
            mappedBy = "busOwner"
    )
    private List<Bus> busList = new ArrayList<>();

    public List<Bus> getBusList() {
        return busList;
    }

    public void setBusList(List<Bus> busList) {
        this.busList = busList;
    }

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_uuid", nullable = false)
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
