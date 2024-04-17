package than.MK.weblaptop.backend.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;
import than.MK.weblaptop.backend.entity.Laptop;

@RepositoryRestResource(path = "laptop")
public interface LaptopRepository extends JpaRepository<Laptop, Integer> {

    Page<Laptop>  findByLaptopNameContaining(@RequestParam("laptopName") String laptopName, Pageable pageable);
    // taking laptop by Brand and BrandID , amazing good job
    Page<Laptop>  findByBrand_BrandID(@RequestParam("brandID") int brandID, Pageable pageable);
    Page<Laptop>  findByModel_ModelID(@RequestParam("modelID") int modelID, Pageable pageable);
    Page<Laptop>  findByBrand_BrandIDAndLaptopNameContaining(@RequestParam("brandID") int brandID, @RequestParam("laptopName") String laptopName, Pageable pageable);

    public boolean existsByLaptopName(String laptopName);

}
