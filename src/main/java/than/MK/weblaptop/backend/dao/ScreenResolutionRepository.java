package than.MK.weblaptop.backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import than.MK.weblaptop.backend.entity.ScreenResolution;

@RepositoryRestResource(path = "screen-resolution")
public interface ScreenResolutionRepository extends JpaRepository<ScreenResolution, Integer> {

}
