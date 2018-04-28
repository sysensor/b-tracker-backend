package com.sysensor.app.repository;

import com.sysensor.app.model.Bus;
import com.sysensor.app.model.BusOwner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BusRepositoryTest {
    @Autowired
    BusRepo busRepo;

    @Test
    @Transactional
    public void busOwnerShouldReturnTheCorrectAttributes() {
        Optional<Bus> busOwnerOptional = busRepo.findById("4028818462642c730162642c8d040008");
        Assert.assertTrue(busOwnerOptional.isPresent());

        Bus bus = busOwnerOptional.get();
        Assert.assertEquals("4028818462642c730162642c8d040008",bus.getUuid());
        Assert.assertEquals("RT120",bus.getRegistration_no());

        BusOwner busOwner = bus.getBusOwner();
        Assert.assertEquals("4028818462642c730162642c8d040003", busOwner.getUuid());
        Assert.assertEquals("Selvam", busOwner.getName());
        Assert.assertEquals("Matara", busOwner.getAddress());
        Assert.assertEquals("0793005675",busOwner.getPhone());
        Assert.assertEquals("selvam",busOwner.getUsername());
        Assert.assertEquals("$2a$10$5SM3OIksgYLL6LU8bb7Raeff2A1nwAuEsF.XoXQq6QxvJwRjh96Jq", busOwner.getPassword());

    }
}
