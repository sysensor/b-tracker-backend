package com.sysensor.app.repository;

import com.sysensor.app.TestConst;
import com.sysensor.app.model.Bus;
import com.sysensor.app.model.BusOwner;
import com.sysensor.app.model.User;
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
public class BusOwnerRepositoryTest {
    @Autowired
    BusOwnerRepo busOwnerRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    BusRepo busRepo;
    @Autowired
    PasswordEncoder passwordEncoder;


    @Test
    @Transactional
    public void busOwnerShouldReturnTheCorrectAttributes() {
        Optional<BusOwner> busOwnerOptional = busOwnerRepo.findById(TestConst.BUS_OWNER_THREE_UUID);
        Assert.assertTrue(busOwnerOptional.isPresent());
        BusOwner busOwner = busOwnerOptional.get();
        User user = busOwner.getUser();
        Assert.assertEquals(TestConst.BUS_OWNER_THREE_UUID, busOwner.getUuid());
        Assert.assertEquals("Selvam", user.getName());
        Assert.assertEquals("Matara", user.getAddress());
        Assert.assertEquals("0793005675", user.getPhone());
        Assert.assertEquals("selvam", user.getUsername());
        Assert.assertEquals("$2a$10$5SM3OIksgYLL6LU8bb7Raeff2A1nwAuEsF.XoXQq6QxvJwRjh96Jq", user.getPassword());

        Bus bus = busOwner.getBusList().get(0);
        Assert.assertEquals(TestConst.BUS_ONE_UUID, bus.getUuid());
        Assert.assertEquals("EY3456", bus.getRegistration_no());

        Optional<BusOwner> busOwnerOptional2 = busOwnerRepo.findById(TestConst.BUS_OWNER_ONE_UUID);
        Assert.assertTrue(busOwnerOptional.isPresent());
        BusOwner busOwner2 = busOwnerOptional2.get();

        Bus bus2 = busOwner2.getBusList().get(0);
        Assert.assertEquals(TestConst.BUS_THREE_UUID, bus2.getUuid());
        Assert.assertEquals("DR5678", bus2.getRegistration_no());

        Bus bus3 = busOwner2.getBusList().get(1);
        Assert.assertEquals(TestConst.BUS_TWO_UUID, bus3.getUuid());
        Assert.assertEquals("MK2345", bus3.getRegistration_no());

    }

    @Test
    @Transactional
    public void busOwnerShouldBeAbleToUpdateAttributes() {
        BusOwner busOwner = new BusOwner();
//        User user = new User();
//
//        user.setName("Damith");
//        user.setAddress("Batakettara");
//        user.setPhone("3456782345");
//        user.setUsername("damith2");
//        user.setPassword("Wow");
//        user.setType("bus owner");
//        user.setStatus(true);
//        user.setUuid("f4cb437-4881-450b-b0fa-4cfe077ba590");

        List<Bus> buses = new ArrayList<>();
        Bus busOne = new Bus();
        busOne.setBusOwner(busOwner);
        busOne.setRegistration_no("KR6789");
        buses.add(busOne);

        Bus busTwo = new Bus();
        busTwo.setBusOwner(busOwner);
        busTwo.setRegistration_no("KH6784");
        buses.add(busTwo);

        User user = userRepo.getOne(TestConst.USER_FIVE_UUID);
        busOwner.setBusList(buses);
        busOwner.setUser(user);
        busOwnerRepo.save(busOwner);

        List<BusOwner> list = busOwnerRepo.findAll();
        Assert.assertEquals(4, list.size());

        BusOwner busOwnerAfter = busOwnerRepo.getOne(busOwner.getUuid());
        Assert.assertEquals(2, busOwnerAfter.getBusList().size());

        busOwnerAfter.getBusList().remove(0);
        busOwnerRepo.save(busOwnerAfter);

        BusOwner busOwnerAfterDeleteOne = busOwnerRepo.getOne(busOwner.getUuid());
        Assert.assertEquals(1, busOwnerAfterDeleteOne.getBusList().size());

        busOwnerRepo.deleteById(busOwner.getUuid());

        Optional<BusOwner> busOwnerAfterDelete = busOwnerRepo.findById(busOwner.getUuid());
        Assert.assertFalse(busOwnerAfterDelete.isPresent());

    }


    @Test
    @Transactional
    public void busOwnerRecordShouldRemoveTheBusesWhenDeletedTheBusOwner() {
        BusOwner busOwner = new BusOwner();
//        User user = new User();
//
//        user.setName("Dinuka");
//        user.setAddress("Piliyandala");
//        user.setPhone("9908768963");
//        user.setUsername("dinuka1");
//        user.setPassword("Nice");
//        user.setType("bus owner");
//        user.setStatus(true);

        List<Bus> buses = new ArrayList<>();
        Bus busOne = new Bus();
        busOne.setBusOwner(busOwner);
        busOne.setRegistration_no("KH6784");
        buses.add(busOne);

        Bus busTwo = new Bus();
        busTwo.setBusOwner(busOwner);
        busTwo.setRegistration_no("HT6789");
        buses.add(busTwo);

//        userRepo.save(user);
        User user = userRepo.getOne(TestConst.USER_FOUR_UUID);
        busOwner.setUser(user);
        busOwner.setBusList(buses);
        busOwnerRepo.save(busOwner);

        List<BusOwner> list = busOwnerRepo.findAll();
        Assert.assertEquals(4, list.size());

        BusOwner busOwnerAfter = busOwnerRepo.getOne(busOwner.getUuid());
//        User userAfter=busOwnerAfter.getUser();
//        Assert.assertEquals(user.getUuid(), userAfter.getUuid());
//        Assert.assertEquals("Dinuka", userAfter.getName());
//        Assert.assertEquals("Piliyandala", userAfter.getAddress());
//        Assert.assertEquals("9908768963", userAfter.getPhone());
//        Assert.assertEquals("dinuka1", userAfter.getUsername());
//        Assert.assertTrue(passwordEncoder.matches(userAfter.getPassword(), passwordEncoder.encode("Nice")));

        Bus busAfter1 = busOwnerAfter.getBusList().get(0);
        Assert.assertNotNull(busAfter1.getUuid());
        Assert.assertEquals("KH6784", busAfter1.getRegistration_no());
        Assert.assertEquals(busOwnerAfter.getUuid(), busAfter1.getBusOwner().getUuid());

        Bus busAfter2 = busOwnerAfter.getBusList().get(1);
        Assert.assertNotNull(busAfter2.getUuid());
        Assert.assertEquals("HT6789", busAfter2.getRegistration_no());
        Assert.assertEquals(busOwnerAfter.getUuid(), busAfter2.getBusOwner().getUuid());

        Bus busFromBusRepo1 = busRepo.getOne(busAfter1.getUuid());
        Assert.assertNotNull(busFromBusRepo1);

        Bus busFromBusRepo2 = busRepo.getOne(busAfter2.getUuid());
        Assert.assertNotNull(busFromBusRepo2);

        busOwnerRepo.delete(busOwnerAfter);

        Optional<Bus> busAfterDeleteOptional1 = busRepo.findById(busAfter1.getUuid());
        Assert.assertFalse(busAfterDeleteOptional1.isPresent());

        Optional<Bus> busAfterDeleteOptional2 = busRepo.findById(busAfter2.getUuid());
        Assert.assertFalse(busAfterDeleteOptional2.isPresent());
    }

    @Test
    @Transactional
    public void busOwnerShouldBeAbleToDeleteTheBuses() {
        BusOwner busOwner = new BusOwner();
//        User user = new User();

//        user.setName("Dinuka");
//        user.setAddress("Piliyandala");
//        user.setPhone("9908768963");
//        user.setUsername("dinuka2");
//        user.setPassword("Nice");
//        user.setType("bus owner");
//        user.setStatus(true);

        List<Bus> buses = new ArrayList<>();
        Bus busOne = new Bus();
        busOne.setBusOwner(busOwner);
        busOne.setRegistration_no("HT6789");
        buses.add(busOne);

        Bus busTwo = new Bus();
        busTwo.setBusOwner(busOwner);
        busTwo.setRegistration_no("JH6743");
        buses.add(busTwo);

//        userRepo.save(user);
        User user = userRepo.getOne(TestConst.USER_NINE_UUID);
        busOwner.setUser(user);
        busOwner.setBusList(buses);
        busOwnerRepo.save(busOwner);

        List<BusOwner> list = busOwnerRepo.findAll();
        Assert.assertEquals(4, list.size());

        BusOwner busOwnerAfter = busOwnerRepo.getOne(busOwner.getUuid());
        Assert.assertEquals(2, busOwnerAfter.getBusList().size());

        busOwnerAfter.getBusList().remove(0);
        busOwnerRepo.save(busOwnerAfter);

        BusOwner busOwnerAfterDeleteOne = busOwnerRepo.getOne(busOwner.getUuid());
        Assert.assertEquals(1, busOwnerAfterDeleteOne.getBusList().size());

    }


}
