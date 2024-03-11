package than.MK.weblaptop.backend.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import than.MK.weblaptop.backend.entity.User;

public interface UserServiceInterface extends UserDetailsService {
    public User findByUserName(String userName);
}
