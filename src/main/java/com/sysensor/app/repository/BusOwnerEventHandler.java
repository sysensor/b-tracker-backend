package com.sysensor.app.repository;


import com.sysensor.app.model.BusOwner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RepositoryEventHandler(BusOwner.class)
public class BusOwnerEventHandler {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private BusOwnerRepo busOwnerRepo;

    @HandleBeforeCreate
    public void handleBusOwnerCreate(BusOwner busOwner) {
        busOwner.setPassword(passwordEncoder.encode(busOwner.getPassword()));
    }

    @HandleBeforeSave
    public void handleBusOwnerUpdate(BusOwner busOwner) {
        if (busOwner.getPassword() == null || busOwner.getPassword().equals("")) {
            //keeps the last password
            Optional<BusOwner> storedUser = busOwnerRepo.findById(busOwner.getUuid());
            busOwner.setPassword(storedUser.get().getPassword());
        }
        else {
            //password change request
            busOwner.setPassword(passwordEncoder.encode(busOwner.getPassword()));
        }
    }
}
