package than.MK.weblaptop.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import than.MK.weblaptop.backend.dao.UserRepository;
import than.MK.weblaptop.backend.entity.Inform;
import than.MK.weblaptop.backend.entity.User;

import java.util.UUID;

@Service
public class AccountService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmailServiceImpl emailServiceImpl;

    public ResponseEntity<?> signUpUser(User user){
        // Check account is exist
        if(userRepository.existsByUserName(user.getUserName())){
            return ResponseEntity.badRequest().body(new Inform("User has existed"));
        }

        if(userRepository.existsByEmail(user.getEmail())){
            return ResponseEntity.badRequest().body(new Inform("Email has existed"));
        }

        // Password encryption
        String encryptPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptPassword);


        //assign and send code active
        user.setCodeActive(createCodeActive());
        user.setActivate(false);


        // Save user into database
        User userRegister = userRepository.save(user);
        sendEmailToActive(user.getEmail(), user.getCodeActive());
        return ResponseEntity.ok("Account has been successfully registered");

    }

    private String createCodeActive(){
        //Create random code
        return UUID.randomUUID().toString();
    }

    // Send code active to customer email
    private ResponseEntity<?> sendEmailToActive(String email, String codeActive){
        String subject = "Active your account at Laptop MK";
        String text ="Please using this code to active your account<" + email + ">:<br/><h1>" + codeActive + "</h1>";
        text += "<br/> Click this link to active your account: ";
        String url = "http://localhost:3000/active/"+email+"/"+codeActive;
        text +="<br/> <a href=" + url+ ">" + url + "</a>";

        emailServiceImpl.sendMessage("than.fullstack.mail.test00@gmail.com",email,subject, text);
        return ResponseEntity.ok("Da gui email thanh cong");
    }

    public ResponseEntity<?> activeAccount(String email, String activeCode){
        User user = userRepository.findByEmail(email);
        if(user == null){
            return ResponseEntity.badRequest().body(new Inform("This account is not exist!"));
        }
        if(user.getActivate()){
            return ResponseEntity.badRequest().body(new Inform("This account have already  been active!"));
        }
        if(activeCode.equals(user.getCodeActive())){
            user.setActivate(true);
            userRepository.save(user);
            return ResponseEntity.ok("Activate your account successfully!");
        }else{
            return ResponseEntity.badRequest().body(new Inform("Your activation code is incorrect"));
        }
    }

}
