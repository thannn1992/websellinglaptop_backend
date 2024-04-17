package than.MK.weblaptop.backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import than.MK.weblaptop.backend.entity.Brand;


@RepositoryRestResource(path = "brand")
public interface BrandRepository extends JpaRepository<Brand, Integer> {

//    @Query("SELECT CASE WHEN COUNT(b) > 0 THEN true ELSE false END FROM Brand b WHERE b.brandName = :brandName")
//    boolean existsByBrandName(@Param("brandName") String brandName);

   public boolean existsByBrandName(String brandName);
}
