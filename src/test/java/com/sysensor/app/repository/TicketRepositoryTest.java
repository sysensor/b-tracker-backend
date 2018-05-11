package com.sysensor.app.repository;

import com.sysensor.app.TestConst;
import com.sysensor.app.model.Ticket;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TicketRepositoryTest {
    @Autowired
    TicketRepo ticketRepo;

    @Test
    @Transactional
    public void ticketsShouldReturnTheCorrectAttributes() {
        Optional<Ticket> ticketOneOptional = ticketRepo.findById(TestConst.TICKET_ONE_UUID);
        Assert.assertTrue(ticketOneOptional.isPresent());

        Ticket ticketOne = ticketOneOptional.get();
        Assert.assertEquals(TestConst.TICKET_ONE_UUID, ticketOne.getUuid());
        Assert.assertEquals("Piliyandala", ticketOne.getStart());
        Assert.assertEquals("Colombo", ticketOne.getDestination());
        Assert.assertEquals(new BigDecimal(100.25), ticketOne.getPrice());
        Assert.assertEquals(true, ticketOne.isStatus());

        Optional<Ticket> ticketTwoOptional = ticketRepo.findById(TestConst.TICKET_TWO_UUID);
        Assert.assertTrue(ticketTwoOptional.isPresent());

        Ticket ticketTwo = ticketTwoOptional.get();
        Assert.assertEquals(TestConst.TICKET_TWO_UUID, ticketTwo.getUuid());
        Assert.assertEquals("Colombo", ticketTwo.getStart());
        Assert.assertEquals("Nugegoda", ticketTwo.getDestination());
        Assert.assertEquals(new BigDecimal(200.25), ticketTwo.getPrice());
        Assert.assertEquals(true, ticketTwo.isStatus());
    }

    @Test
    @Transactional
    public void ticketShouldBeAbleToCreateUpdateAndDelete() {
        Ticket ticket = new Ticket();
        ticket.setStart("Madapatha");
        ticket.setDestination("Piliyandala");
        ticket.setPrice(new BigDecimal(12.50));
        ticket.setStatus(true);

        Ticket ticketAfterSave = ticketRepo.save(ticket);
        String UUID_SAVED_TICKET = ticketAfterSave.getUuid();

        Assert.assertEquals(3, ticketRepo.findAll().size());
        Assert.assertEquals("Madapatha", ticketAfterSave.getStart());
        Assert.assertEquals("Piliyandala", ticketAfterSave.getDestination());
        Assert.assertEquals(new BigDecimal(12.50), ticketAfterSave.getPrice());
        Assert.assertEquals(true, ticketAfterSave.isStatus());

        ticketRepo.deleteById(UUID_SAVED_TICKET);

        Optional<Ticket> ticketAfterDeleteOptional = ticketRepo.findById(UUID_SAVED_TICKET);
        Assert.assertFalse(ticketAfterDeleteOptional.isPresent());
        Assert.assertEquals(2, ticketRepo.findAll().size());
    }

}
