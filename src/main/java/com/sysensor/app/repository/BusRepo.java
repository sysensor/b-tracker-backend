package com.sysensor.app.repository;

import com.sysensor.app.config.APIConfig;
import com.sysensor.app.model.Bus;
import com.sysensor.app.model.BusOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

@RepositoryRestResource(collectionResourceRel = APIConfig.BUS, path = APIConfig.BUS)
public interface BusRepo extends JpaRepository<Bus, String> {

}
