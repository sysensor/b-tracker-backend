package com.sysensor.app.repository;

import com.sysensor.app.TestConst;
import com.sysensor.app.model.Passenger;
import com.sysensor.app.model.Ticket;
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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PassengerRepositoryTest {
    @Autowired
    PassengerRepo passengerRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    TicketRepo ticketRepo;


    @Test
    @Transactional
    public void PassengerShouldReturnCorrectAttributes() {
        Optional<Passenger> passengerById = passengerRepo.findById(TestConst.PASSENGER_ONE_UUID);
        Assert.assertTrue(passengerById.isPresent());
        Passenger passenger = passengerById.get();
        User user = passenger.getUser();
        Assert.assertEquals("sena", user.getUsername());
        Assert.assertEquals("$2a$10$5SM3OIksgYLL6LU8bb7Raeff2A1nwAuEsF.XoXQq6QxvJwRjh96Jq", user.getPassword());
        Assert.assertEquals("Sena", user.getName());
        Assert.assertEquals("Piliyandala", user.getAddress());
        Assert.assertEquals("0773676240", user.getPhone());

        Ticket ticket = passenger.getTicketList().get(0);
        Assert.assertEquals("Piliyandala", ticket.getStart());
        Assert.assertEquals("Colombo", ticket.getDestination());
        Assert.assertEquals(new BigDecimal(100.25), ticket.getPrice());
        Assert.assertEquals(TestConst.TICKET_ONE_UUID, ticket.getUuid());
    }

    @Test
    @Transactional
    public void PassengerShouldBeAbleToUpdateAttributes() {
        Passenger passenger = new Passenger();
        User user = new User();
        user.setUsername("damith");
        user.setName("Damith");
        user.setPhone("0702737009");
        user.setAddress("219, Sarabhoomi");
        user.setPassword("Wow");

        user.setType("1");
        user.setStatus(true);

        List<Ticket> tickets = new ArrayList<>();
        Ticket ticketOne = new Ticket();
        ticketOne.setPassenger(passenger);
        ticketOne.setDestination("Kahapola");
        ticketOne.setPrice(new BigDecimal(100.25));
        ticketOne.setStart("Piliyandala");
        ticketOne.setStatus(true);
        tickets.add(ticketOne);

        Ticket ticketTwo = new Ticket();
        ticketTwo.setPassenger(passenger);
        ticketTwo.setDestination("Madapatha");
        ticketTwo.setPrice(new BigDecimal(100.25));
        ticketTwo.setStart("Piliyandala");
        ticketTwo.setStatus(true);
        tickets.add(ticketTwo);

        userRepo.save(user);
        passenger.setUser(user);
        passenger.setTicketList(tickets);
        passengerRepo.save(passenger);

        List<Passenger> list = passengerRepo.findAll();
        Assert.assertEquals(4, list.size());

        Passenger passengerAfter = passengerRepo.getOne(passenger.getUuid());
        Assert.assertEquals(2, passengerAfter.getTicketList().size());

        passengerAfter.getTicketList().remove(0);
        passengerRepo.save(passengerAfter);

        Passenger passengerAfterDeleteOne = passengerRepo.getOne(passenger.getUuid());
        Assert.assertEquals(1, passengerAfterDeleteOne.getTicketList().size());

        passengerRepo.deleteById(passenger.getUuid());

        Optional<Passenger> busOwnerAfterDelete = passengerRepo.findById(passenger.getUuid());
        Assert.assertFalse(busOwnerAfterDelete.isPresent());

    }

    @Test
    @Transactional
    public void passengerRecordShouldRemoveTheTicketsWhenDeletedThePassenger() {
        Passenger passenger = new Passenger();
        User user = new User();
        user.setUsername("kasun");
        user.setPassword("nice");
        user.setPhone("0702737232");
        user.setAddress("219, Sarabhoomi");
        user.setType("bus owner");
        user.setStatus(true);
        userRepo.save(user);
        List<Ticket> tickets = new ArrayList<>();
        Ticket ticketOne = new Ticket();
        ticketOne.setPassenger(passenger);
        ticketOne.setDestination("Kahapola");
        ticketOne.setPrice(new BigDecimal(100.25));
        ticketOne.setStart("Piliyandala");
        ticketOne.setStatus(true);
        tickets.add(ticketOne);

        Ticket ticketTwo = new Ticket();
        ticketTwo.setPassenger(passenger);
        ticketTwo.setDestination("Madapatha");
        ticketTwo.setPrice(new BigDecimal(200.25));
        ticketTwo.setStart("Piliyandala");
        ticketTwo.setStatus(true);
        tickets.add(ticketTwo);

        passenger.setUser(user);
        passenger.setTicketList(tickets);
        passengerRepo.save(passenger);
        List<Passenger> list = passengerRepo.findAll();
        Assert.assertEquals(4, list.size());

        Passenger passengerAfter = passengerRepo.getOne(passenger.getUuid());
        User user1 = passengerAfter.getUser();
//        Assert.assertEquals(user.getUuid(), user1.getUuid());
        Assert.assertEquals("kasun", user1.getUsername());
        Assert.assertTrue(passwordEncoder.matches(user1.getPassword(), passwordEncoder.encode("nice")));

        Ticket ticketAfter1 = passengerAfter.getTicketList().get(0);
        Assert.assertNotNull(ticketAfter1.getUuid());
        Assert.assertEquals("Kahapola", ticketAfter1.getDestination());
        Assert.assertEquals(new BigDecimal(100.25), ticketAfter1.getPrice());
        Assert.assertEquals("Piliyandala", ticketAfter1.getStart());
        Assert.assertEquals(ticketOne.getUuid(), ticketAfter1.getUuid());
        Assert.assertEquals(passenger.getUuid(), ticketAfter1.getPassenger().getUuid());

        Ticket ticketAfter2 = passengerAfter.getTicketList().get(1);
        Assert.assertNotNull(ticketAfter2.getUuid());
        Assert.assertEquals("Madapatha", ticketAfter2.getDestination());
        Assert.assertEquals(new BigDecimal(200.25), ticketAfter2.getPrice());
        Assert.assertEquals("Piliyandala", ticketAfter2.getStart());
        Assert.assertEquals(ticketTwo.getUuid(), ticketAfter2.getUuid());
        Assert.assertEquals(passenger.getUuid(), ticketAfter2.getPassenger().getUuid());

        Ticket ticketFromTicketRepo1 = ticketRepo.getOne(ticketAfter1.getUuid());
        Assert.assertNotNull(ticketFromTicketRepo1);

        Ticket ticketFromTicketRepo2 = ticketRepo.getOne(ticketAfter2.getUuid());
        Assert.assertNotNull(ticketFromTicketRepo2);

        passengerRepo.delete(passengerAfter);

        Optional<Ticket> busAfterDeleteOptional1 = ticketRepo.findById(ticketAfter1.getUuid());
        Assert.assertFalse(busAfterDeleteOptional1.isPresent());

        Optional<Ticket> busAfterDeleteOptional2 = ticketRepo.findById(ticketAfter2.getUuid());
        Assert.assertFalse(busAfterDeleteOptional2.isPresent());
    }


}
