package com.sysensor.app.repository;

import com.sysensor.app.TestConst;
import com.sysensor.app.model.Bus;
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
        Optional<BusRoute> busRouteOptional = busRouteRepo.findById(TestConst.BUS_ROUTE_ONE_UUID);
        Assert.assertTrue(busRouteOptional.isPresent());
        BusRoute busRoute = busRouteOptional.get();
        Assert.assertEquals(TestConst.BUS_ROUTE_ONE_UUID, busRoute.getUuid());
        Assert.assertEquals("RT120", busRoute.getName());
        Assert.assertEquals("Piliyandala", busRoute.getStart());
        Assert.assertEquals("Colombo", busRoute.getDestination());

        Bus bus = busRoute.getBusList().get(0);
        Assert.assertEquals(TestConst.BUS_THREE_UUID, bus.getUuid());
        Assert.assertEquals("DR5678", bus.getRegistration_no());

        Optional<BusRoute> busRouteOptional2 = busRouteRepo.findById(TestConst.BUS_ROUTE_TWO_UUID);
        Assert.assertTrue(busRouteOptional.isPresent());
        BusRoute busRoute2 = busRouteOptional2.get();

        Bus bus2 = busRoute2.getBusList().get(0);
        Assert.assertEquals(TestConst.BUS_TWO_UUID, bus2.getUuid());
        Assert.assertEquals("MK2345", bus2.getRegistration_no());

        Bus bus3 = busRoute2.getBusList().get(1);
        Assert.assertEquals(TestConst.BUS_ONE_UUID, bus3.getUuid());
        Assert.assertEquals("EY3456", bus3.getRegistration_no());

    }

    @Test
    @Transactional
    public void busRouteShouldBeAbleToUpdateAttributesAndDelete() {
        //Create BusRoute
        BusRoute busRoute = new BusRoute();
        busRoute.setName("RT157");
        busRoute.setStart("Madapatha");
        busRoute.setDestination("Piliyandala");
        busRouteRepo.save(busRoute);

        String BUSROUTE_UUID = busRoute.getUuid();

        //Verify after Save
        BusRoute busRouteAfterSave = busRouteRepo.getOne(BUSROUTE_UUID);
        Assert.assertNotNull(busRouteAfterSave);
        Assert.assertEquals(0, busRouteAfterSave.getBusList().size());

        //Add existing BUS to the route
        List<Bus> buses = new ArrayList<>();
        Bus busOne = busRepo.getOne(TestConst.BUS_THREE_UUID);
        buses.add(busOne);

        busRoute.setBusList(buses);
        busRoute.setStart("Kahapola");
        busRouteRepo.save(busRoute);
        Assert.assertEquals(BUSROUTE_UUID, busRoute.getUuid());

        //Verify after update
        BusRoute busRouteAfterUpdate = busRouteRepo.getOne(BUSROUTE_UUID);
        Assert.assertNotNull(busRouteAfterUpdate);
        Assert.assertEquals("Kahapola", busRouteAfterUpdate.getStart());
        Assert.assertEquals(1, busRouteAfterUpdate.getBusList().size());
        Assert.assertEquals(TestConst.BUS_THREE_UUID, busRouteAfterUpdate.getBusList().get(0).getUuid());

        //Delete the BusRoute
        busRouteRepo.deleteById(BUSROUTE_UUID);
        Optional<BusRoute> busRouteAfterDeleteOptional = busRouteRepo.findById(BUSROUTE_UUID);
        Assert.assertFalse(busRouteAfterDeleteOptional.isPresent());

        Bus busAfterDelete = busRepo.getOne(TestConst.BUS_THREE_UUID);
        Assert.assertNotNull(busAfterDelete);

    }

}
