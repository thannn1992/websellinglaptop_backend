package than.MK.weblaptop.backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import than.MK.weblaptop.backend.entity.AdministrativeRegions;

@RepositoryRestResource(path = "administrative_units")
public interface AdministrativeUnitsRepository  extends JpaRepository<AdministrativeRegions, Integer> {
}
