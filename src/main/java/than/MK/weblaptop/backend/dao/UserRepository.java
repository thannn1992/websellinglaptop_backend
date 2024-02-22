package than.MK.weblaptop.backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import than.MK.weblaptop.backend.entity.User;

@RepositoryRestResource(path = "user")
public interface UserRepository extends JpaRepository<User, Integer>{

}
