package than.MK.weblaptop.backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import than.MK.weblaptop.backend.entity.Provinces;

@RepositoryRestResource(path = "provinces")
public interface ProvincesRepository extends JpaRepository<Provinces, Integer> {

}
