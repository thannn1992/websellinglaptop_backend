package than.MK.weblaptop.backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;


import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.RequestParam;
import than.MK.weblaptop.backend.entity.User;

@RepositoryRestResource(path = "user")
public interface UserRepository extends JpaRepository<User, Integer>{
    public boolean existsByUserName(String userName);

    public boolean existsByEmail(String email);

    public User findByUserName(String userName);

    public User findByEmail(String email);

}
