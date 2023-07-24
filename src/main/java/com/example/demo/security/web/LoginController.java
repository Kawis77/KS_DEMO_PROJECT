package com.example.demo.security.web;

import com.example.demo.security.dao.LoginRequest;
import com.example.demo.security.service.LoginService;
import com.example.demo.users.dao.entity.UserEntity;
import com.example.demo.users.dao.repository.UserRepository;
import com.example.demo.users.service.CustomDetailUserService;
import com.example.demo.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/api/security/")
public class LoginController {

    @Autowired
    private CustomDetailUserService customDetailUserService;

    @Autowired
    private LoginService loginService;

    @PostMapping("/customlogin")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        if (loginRequest != null && loginRequest.getUsername() != null && loginRequest.getPassword() != null) {
            UserDetails user = customDetailUserService.loadUserByUsername(loginRequest.getUsername());
            if (user != null) {
                BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

                String bCryptPassword = user.getPassword().replaceAll("\\{.*?}", "");

                if (bCryptPasswordEncoder.matches(loginRequest.getPassword(), bCryptPassword )) {
                    String token = loginService.generateJwtToken(loginRequest.getUsername());
                    return ResponseEntity.ok(token);
                } else {
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect login details");
                }
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect login details");
    }
}
