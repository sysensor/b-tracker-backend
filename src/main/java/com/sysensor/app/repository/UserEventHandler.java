package com.sysensor.app.repository;


import com.sysensor.app.config.CommonConfig;
import com.sysensor.app.model.BusOwner;
import com.sysensor.app.model.Passenger;
import com.sysensor.app.model.TimeKeeper;
import com.sysensor.app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RepositoryEventHandler(UserRepo.class)
public class UserEventHandler {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private BusOwnerRepo busOwnerRepo;

    @Autowired
    private TimeKeeperRepo timeKeeperRepo;

    @Autowired
    private PassengerRepo passengerRepo;

    @HandleBeforeCreate
    public void handleUserCreate(User user) {
        if (user.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
    }

    @HandleAfterCreate
    public void handleUserAfterCreate(User user) {
        switch (user.getType()) {
            case CommonConfig.TYPE_BUS_OWNER: // restrict type change
                user.setType(CommonConfig.TYPE_BUS_OWNER);
                BusOwner busOwner = new BusOwner();
                busOwner.setUser(user);
                busOwnerRepo.save(busOwner);
                break;

            case CommonConfig.TYPE_PASSENGER:
                user.setType(CommonConfig.TYPE_PASSENGER);
                Passenger passenger = new Passenger();
                passenger.setUser(user);
                passengerRepo.save(passenger);
                break;

            case CommonConfig.TYPE_TIME_KEEPER:
                user.setType(CommonConfig.TYPE_TIME_KEEPER);
                TimeKeeper timeKeeper = new TimeKeeper();
                timeKeeper.setUser(user);
                timeKeeperRepo.save(timeKeeper);
                break;
            default:
                return;

        }
    }

    @HandleBeforeSave
    public void handleUserUpdate(User user) {
        Optional<User> userOptional = userRepo.findById(user.getUuid());
        User storedUser = userOptional.get(); //get stored user
        user.setUsername(storedUser.getUsername()); //restrict username change
        switch (storedUser.getType()) {
            case CommonConfig.TYPE_BUS_OWNER: // restrict type change
                user.setType(CommonConfig.TYPE_BUS_OWNER);
                break;
            case CommonConfig.TYPE_PASSENGER:
                user.setType(CommonConfig.TYPE_PASSENGER);
                break;
            case CommonConfig.TYPE_TIME_KEEPER:
                user.setType(CommonConfig.TYPE_TIME_KEEPER);
                break;
            default:
                return;
        }
        if (user.getPassword() == null || user.getPassword().equals("")) {
            //keeps the last password
            user.setPassword(storedUser.getPassword());
        } else {
            //password change request
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
    }


}
