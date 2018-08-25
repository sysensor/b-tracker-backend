package com.sysensor.app.repository;

import com.sysensor.app.config.APIConfig;
import com.sysensor.app.model.TimeKeeper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = {APIConfig.CROSS_ORIGIN_URL})
@RepositoryRestResource(collectionResourceRel = APIConfig.TIME_KEEPER, path = APIConfig.TIME_KEEPER)
public interface TimeKeeperRepo extends JpaRepository<TimeKeeper, String> {
}
