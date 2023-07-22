package com.example.demo.users.web;

import com.example.demo.users.dao.entity.UserEntity;
import com.example.demo.users.service.UserManagerService;
import com.example.demo.users.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/user")
public class RegistrationController {

    private final UserManagerService userMenagerService;

    public RegistrationController(UserService userService, UserManagerService userMenagerService) {
        this.userMenagerService = userMenagerService;
    }


    @GetMapping("/test")
    public String testMethode() {
        System.out.println("jak sie masz");
        return "jak sie masz?";
    }

    @GetMapping("/register")
    public String proccesRegistriation(UserEntity userEntity) {
        userEntity.setPassword("admin");
        userEntity.setUsername("admin");
        userEntity.setRole("USER");
        userMenagerService.registerUser(userEntity);
        return "Success";
    }
}