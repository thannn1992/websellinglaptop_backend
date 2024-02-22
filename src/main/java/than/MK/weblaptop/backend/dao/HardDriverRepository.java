package than.MK.weblaptop.backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import than.MK.weblaptop.backend.entity.HardDriver;


@RepositoryRestResource(path = "hard-driver")
public interface HardDriverRepository extends JpaRepository<HardDriver, Integer> {

}
