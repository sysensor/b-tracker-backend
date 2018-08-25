package com.sysensor.app.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Passenger extends UUIDBaseEntity {

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true, fetch = FetchType.LAZY,
            mappedBy = "passenger"
    )
    private List<Ticket> ticketList = new ArrayList<>();

    public List<Ticket> getTicketList() {
        return ticketList;
    }

    public void setTicketList(List<Ticket> ticketList) {
        this.ticketList = ticketList;
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
