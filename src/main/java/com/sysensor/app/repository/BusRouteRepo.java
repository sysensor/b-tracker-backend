package com.sysensor.app.repository;

import com.sysensor.app.config.APIConfig;
import com.sysensor.app.model.BusRoute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = APIConfig.BUS_ROUTE, path = APIConfig.BUS_ROUTE)
public interface BusRouteRepo extends JpaRepository<BusRoute, String> {

}
