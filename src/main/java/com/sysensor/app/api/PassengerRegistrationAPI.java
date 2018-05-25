package com.sysensor.app.api;

import com.sysensor.app.common.APIUtility;
import com.sysensor.app.config.APIConfig;
import com.sysensor.app.model.Passenger;
import com.sysensor.app.repository.PassengerRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(APIConfig.API)
@CrossOrigin(origins = APIConfig.CROSS_ORIGIN_URL)
@Transactional
public class PassengerRegistrationAPI {

    @Autowired
    PassengerRepo passengerRepo;

    Logger LOG = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/passenger/{test}", method = RequestMethod.GET, produces = APIUtility.APPLICATION_JSON)
    public Passenger getPassenger(@RequestHeader HttpHeaders headers, @PathVariable String test) {
        APIUtility.printHeaders(headers, LOG);
        Optional<Passenger> passenger = passengerRepo.findById(test);
        if(passenger.isPresent()){
            return passenger.get();
        }

        // LOG.info("Returning the Latest Location" + busOwner.toString());
        return null;
    }
}
