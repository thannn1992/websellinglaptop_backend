package than.MK.weblaptop.backend.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;
import than.MK.weblaptop.backend.entity.Districts;
import than.MK.weblaptop.backend.entity.Laptop;
import than.MK.weblaptop.backend.entity.Model;
import than.MK.weblaptop.backend.entity.User;

@RepositoryRestResource(path = "model")
public interface ModelRepository extends JpaRepository<Model, Integer>{
    Page<Model>  findByBrand_BrandID(@RequestParam("brandID") int brandID, Pageable pageable);

    public Model findByModelName(String modelName);

}
