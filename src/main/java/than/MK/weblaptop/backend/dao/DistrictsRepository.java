package than.MK.weblaptop.backend.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.RequestParam;
import than.MK.weblaptop.backend.entity.Districts;
import than.MK.weblaptop.backend.entity.Laptop;

@RepositoryRestResource(path = "districts")
public interface DistrictsRepository extends JpaRepository<Districts, Integer> {

 Page<Districts> findByProvinceCode(@RequestParam("provinceCode") String provinceCode, Pageable pageable);
}
