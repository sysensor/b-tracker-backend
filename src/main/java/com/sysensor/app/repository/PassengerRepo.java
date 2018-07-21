package com.sysensor.app.repository;

import com.sysensor.app.config.APIConfig;
import com.sysensor.app.model.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = {APIConfig.CROSS_ORIGIN_URL})
@RepositoryRestResource(collectionResourceRel = APIConfig.PASSENGER, path = APIConfig.PASSENGER)
public interface PassengerRepo extends JpaRepository<Passenger, String> {

}
