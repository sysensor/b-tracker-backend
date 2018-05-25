package com.sysensor.app.api;

import com.sysensor.app.common.APIUtility;
import com.sysensor.app.config.APIConfig;
import com.sysensor.app.model.Location;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(APIConfig.API)
@CrossOrigin(origins = {APIConfig.CROSS_ORIGIN_URL})
@Transactional
public class GPSSignalAPI {

    Logger LOG = LoggerFactory.getLogger(this.getClass());


    @RequestMapping(value = "/signal", method = RequestMethod.GET, produces = APIUtility.APPLICATION_JSON)
    public Location getLatestSignal(@RequestHeader HttpHeaders headers) {
        APIUtility.printHeaders(headers, LOG);
        Location location = new Location();
        location.setLat("41.619549");
        location.setLng("-93.598022");

        LOG.info("Returning the Latest Location" + location.toString());
        return location;
    }

    @RequestMapping(value = "/signal", method = RequestMethod.POST, consumes = APIUtility.APPLICATION_JSON, produces = APIUtility.APPLICATION_JSON)
    @PreAuthorize("hasRole('ADMIN')")
    public Location postSignal(@RequestHeader HttpHeaders headers, @RequestBody Location location) {
        APIUtility.printHeaders(headers, LOG);
        LOG.info(location.toString());
        return location;
    }
}
