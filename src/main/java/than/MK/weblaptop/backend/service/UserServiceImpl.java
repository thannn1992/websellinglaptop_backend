package than.MK.weblaptop.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import than.MK.weblaptop.backend.dao.RightsRepository;
import than.MK.weblaptop.backend.dao.UserRepository;
import than.MK.weblaptop.backend.entity.Rights;
import than.MK.weblaptop.backend.entity.User;

import java.util.Collection;
import java.util.stream.Collectors;
@Service
public class UserServiceImpl implements UserServiceInterface{
    private UserRepository userRepository;
    private RightsRepository rightsRepository;
    @Autowired
    public UserServiceImpl(UserRepository userRepository, RightsRepository rightsRepository) {
        this.userRepository = userRepository;
        this.rightsRepository = rightsRepository;
    }

    @Override
    public User findByUserName(String usersName) {
        return userRepository.findByUserName(usersName);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("Tài khoản không tồn tại!");
        }
        // lấy user này import org.springframework.security.core.userdetails.UserDetails;
        org.springframework.security.core.userdetails.User userDetails = new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), rolesToAuthorities(user.getListRight()));
        return userDetails;
    }

    private Collection<? extends GrantedAuthority> rolesToAuthorities(Collection<Rights> rights) {
        return rights.stream().map(rights1 -> new SimpleGrantedAuthority(rights1.getRightName())).collect(Collectors.toList());
    }
}

