package com.sysensor.app.repository;

import com.sysensor.app.model.Passenger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PassengerRepositoryTest {
    @Autowired
    PassengerRepo passengerRepo;

    @Autowired
    Passenger passenger;

    @Test
    @Transactional
    public void PassengerShouldReturnCorrectAttributes(){



    }
}
