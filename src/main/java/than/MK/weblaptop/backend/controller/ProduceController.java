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
import than.MK.weblaptop.backend.entity.Laptop;
import than.MK.weblaptop.backend.entity.Picture;
import than.MK.weblaptop.backend.entity.User;
import than.MK.weblaptop.backend.security.JwtResponse;
import than.MK.weblaptop.backend.security.LoginRequest;
import than.MK.weblaptop.backend.service.AccountService;
import than.MK.weblaptop.backend.service.JwtService;
import than.MK.weblaptop.backend.service.ProduceService;
import than.MK.weblaptop.backend.service.UserServiceInterface;

import java.util.List;

@RestController // response JSON
@CrossOrigin(origins = "http://localhost:3000") //Allow requests from 'http://localhost:3000'
//@CrossOrigin(origins = "*") // allow all link
@RequestMapping("/api/produce")
public class ProduceController {
    @Autowired
    private ProduceService produceService;

    // response website (backend response for frontend
    @PostMapping("/add")
    public ResponseEntity<?> addProduce( @RequestBody Laptop laptop) {

        ResponseEntity<?> response = produceService.addProduce(laptop);
        return response;
    }

}
