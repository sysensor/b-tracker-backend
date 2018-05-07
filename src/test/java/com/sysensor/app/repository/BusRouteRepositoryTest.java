package com.sysensor.app.repository;

import com.sysensor.app.model.Bus;
import com.sysensor.app.model.BusOwner;
import com.sysensor.app.model.BusRoute;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BusRouteRepositoryTest {
    @Autowired
    BusRouteRepo busRouteRepo;
    @Autowired
    BusRepo busRepo;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    @Transactional
    public void busRouteShouldReturnTheCorrectAttributes() {
        Optional<BusRoute> busRouteOptional = busRouteRepo.findById("bf4cb437-4881-450b-b0fa-4cfe077ba547");
        Assert.assertTrue(busRouteOptional.isPresent());
        BusRoute busRoute = busRouteOptional.get();
        Assert.assertEquals("bf4cb437-4881-450b-b0fa-4cfe077ba547", busRoute.getUuid());
        Assert.assertEquals("RT120", busRoute.getName());
        Assert.assertEquals("Piliyandala", busRoute.getStart());
        Assert.assertEquals("Colombo", busRoute.getDestination());

        Bus bus = busRoute.getBusList().get(0);
        Assert.assertEquals("4028818462642c730162642c8d040010", bus.getUuid());
        Assert.assertEquals("DR5678", bus.getRegistration_no());

        Optional<BusRoute> busRouteOptional2 = busRouteRepo.findById("bf4cb437-4881-450b-b0fa-4cfe077ba548");
        Assert.assertTrue(busRouteOptional.isPresent());
        BusRoute busRoute2 = busRouteOptional2.get();

        Bus bus2 = busRoute2.getBusList().get(0);
        Assert.assertEquals("4028818462642c730162642c8d040009", bus2.getUuid());
        Assert.assertEquals("MK2345", bus2.getRegistration_no());

        Bus bus3 = busRoute2.getBusList().get(1);
        Assert.assertEquals("4028818462642c730162642c8d040008", bus3.getUuid());
        Assert.assertEquals("EY3456", bus3.getRegistration_no());

    }

    @Test
    @Transactional
    public void busOwnerShouldBeAbleToUpdateAttributes() {


    }


    @Test
    @Transactional
    public void busOwnerRecordShouldRemoveTheBusesWhenDeletedTheBusOwner() {

    }

    @Test
    @Transactional
    public void busOwnerShouldBeAbleToDeleteTheBuses() {


    }


}
