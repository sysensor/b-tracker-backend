package com.sysensor.app.controller;

import com.sysensor.app.common.APIUtility;
import com.sysensor.app.config.APIConfig;
import com.sysensor.app.model.BusOwner;
import com.sysensor.app.repository.BusOwnerRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(APIConfig.API)
@CrossOrigin(origins = APIConfig.CROSS_ORIGIN_URL)
public class BusOwnerRegistrationAPI {

    @Autowired
    BusOwnerRepo busOwnerRepo;

    Logger LOG = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/bus-owner/{test}", method = RequestMethod.GET, produces = APIUtility.APPLICATION_JSON)
    public BusOwner getLatestSignal(@RequestHeader HttpHeaders headers, @PathVariable String test) {
        APIUtility.printHeaders(headers, LOG);
        Optional<BusOwner> busOwner = busOwnerRepo.findById(test);
        if(busOwner.isPresent()){
            return busOwner.get();
        }

       // LOG.info("Returning the Latest Location" + busOwner.toString());
        return null;
    }
}
