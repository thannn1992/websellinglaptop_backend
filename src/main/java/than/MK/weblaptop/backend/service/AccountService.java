package than.MK.weblaptop.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import than.MK.weblaptop.backend.dao.RightsRepository;
import than.MK.weblaptop.backend.dao.UserRepository;
import than.MK.weblaptop.backend.entity.Inform;
import than.MK.weblaptop.backend.entity.Rights;
import than.MK.weblaptop.backend.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class AccountService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RightsRepository rightsRepository;
    @Autowired
    private EmailServiceImpl emailServiceImpl;

    public ResponseEntity<?> signUpUser(User user) {
        // Check account is exist
        if (userRepository.existsByUserName(user.getUserName())) {
            return ResponseEntity.badRequest().body(new Inform("User has existed"));
        }

        if (userRepository.existsByEmail(user.getEmail())) {
            return ResponseEntity.badRequest().body(new Inform("Email has existed"));
        }

        // Password encryption
        String encryptPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptPassword);

        //assign and send code active
        user.setCodeActive(createCodeActive());
        user.setActivate(false);

        //set listRight
        Rights right = rightsRepository.findByRightName("User");
        List<Rights> listRights = new ArrayList<>();
        listRights.add(right);

        //thêm vào user_right user_id và right_id trong sql
        user.setListRight(listRights);

        // Save user into database
        User userRegister = userRepository.save(user);

        sendEmailToActive(user.getEmail(), user.getCodeActive());
        return ResponseEntity.ok("Account has been successfully registered");

    }

    public ResponseEntity<?> updateUser(User user) {
        User userTemp = userRepository.findByUserName(user.getUserName());
        if (userTemp == null) {
            return ResponseEntity.badRequest().body(new Inform("This account is not exist!"));
        } else{
            userTemp.setFirstName(user.getFirstName());
            userTemp.setLastName(user.getLastName());
            userTemp.setEmail(user.getEmail());
            userTemp.setPhoneNumber(user.getPhoneNumber());
            userTemp.setGender(user.getGender());
            userTemp.setBirthDay(user.getBirthDay());
            userTemp.setAvatar(user.getAvatar());
            userTemp.setAddress(user.getAddress());
        }
        User userUpdate = userRepository.save(userTemp);
        return ResponseEntity.ok("Account has been update successfully");
    }

    private String createCodeActive() {
        //Create random code
        return UUID.randomUUID().toString();
    }

    // Send code active to customer email
    private ResponseEntity<?> sendEmailToActive(String email, String codeActive) {
        String subject = "Active your account at Laptop MK";
        String text = "Please using this code to active your account<" + email + ">:<br/><h1>" + codeActive + "</h1>";
        text += "<br/> Click this link to active your account: ";
        String url = "http://localhost:3000/active/" + email + "/" + codeActive;
        text += "<br/> <a href=" + url + ">" + url + "</a>";

        emailServiceImpl.sendMessage("than.fullstack.mail.test00@gmail.com", email, subject, text);
        return ResponseEntity.ok("Da gui email thanh cong");
    }

    public ResponseEntity<?> activeAccount(String email, String activeCode) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            return ResponseEntity.badRequest().body(new Inform("This account is not exist!"));
        }
        if (user.getActivate()) {
            return ResponseEntity.badRequest().body(new Inform("This account have already  been active!"));
        }
        if (activeCode.equals(user.getCodeActive())) {
            user.setActivate(true);
            userRepository.save(user);
            return ResponseEntity.ok("Activate your account successfully!");
        } else {
            return ResponseEntity.badRequest().body(new Inform("Your activation code is incorrect"));
        }
    }

}
