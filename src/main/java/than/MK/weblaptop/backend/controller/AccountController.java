package than.MK.weblaptop.backend.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import than.MK.weblaptop.backend.entity.User;
import than.MK.weblaptop.backend.security.JwtResponse;
import than.MK.weblaptop.backend.security.LoginRequest;
import than.MK.weblaptop.backend.service.AccountService;
import than.MK.weblaptop.backend.service.JwtService;
import than.MK.weblaptop.backend.service.UserServiceInterface;

@RestController // response JSON
@CrossOrigin(origins = "http://localhost:3000") //Allow requests from 'http://localhost:3000'
//@CrossOrigin(origins = "*") // allow all link
@RequestMapping("/api/account")
public class AccountController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserServiceInterface userServiceInterface;


    // response website (backend response for frontend
    @PostMapping("/register")
    public ResponseEntity<?> registerAccount(@Validated @RequestBody User user) {
        ResponseEntity<?> response = accountService.signUpUser(user);
        return response;
    }

    @GetMapping("/active")
    public ResponseEntity<?> activeAccount(@RequestParam String email, @RequestParam String activecode) {
        ResponseEntity<?> response = accountService.activeAccount(email, activecode);
        return response;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        //Verify account by username and pass word
        try {
            //Auto check in DataBase have user and password or not
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );
            //if verify success, create token JWT
            if (authentication.isAuthenticated()) {
                final String jwt = jwtService.generateToken(loginRequest.getUsername());
                return ResponseEntity.ok(new JwtResponse(jwt));
            }

        } catch (AuthenticationException e) {
            // verify don't success, response error or inform
            return ResponseEntity.badRequest().body("Tên đăng nhập hoặc mật khẩu không chính xác.");
        }
        return ResponseEntity.badRequest().body("Xác thực không thành công.");
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateAccount(@RequestBody User user, HttpServletRequest request){

        String authHeader = request.getHeader("Authorization");
        String token = null;
        String username = null;
        if(authHeader!=null && authHeader.startsWith("Bearer ")){
            token = authHeader.substring(7);
            username = jwtService.extractUserName(token);
        }
      if(username != null){
          user.setUserName(username);
      }
        ResponseEntity<?> response = accountService.updateUser(user);
        return response;
    }

//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
//        // Xác thực người dùng bằng tên đăng nhập và mật khẩu
//        try {
//            Authentication authentication = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
//            );
//            // Nếu xác thực thành công, tạo token JWT
//            if(authentication.isAuthenticated()){
//                final String jwt = jwtService.generateToken(loginRequest.getUsername());
//                return ResponseEntity.ok(new JwtResponse(jwt));
//            }
//        }catch (AuthenticationException e){
//            // Xác thực không thành công, trả về lỗi hoặc thông báo
//            return ResponseEntity.badRequest().body("Tên đăng nhập hặc mật khẩu không chính xác.");
//        }
//        return ResponseEntity.badRequest().body("Xác thực không thành công.");
//    }
}
