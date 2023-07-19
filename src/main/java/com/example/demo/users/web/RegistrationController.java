package com.example.demo.users.web;

import com.example.demo.users.dao.entity.UserEntity;
import com.example.demo.users.service.UserManagerService;
import com.example.demo.users.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/user")
public class RegistrationController {

    private final UserManagerService userMenagerService;

    public RegistrationController(UserService userService, UserManagerService userMenagerService) {
        this.userMenagerService = userMenagerService;
    }

    @GetMapping("/register")
    public String prepareRegistration(Model model) {
        model.addAttribute("user", new UserEntity());
        return "registration";

    }

    @PostMapping("/register")
    public String proccesRegistriation(UserEntity userEntity) {
        userMenagerService.registerUser(userEntity);
        return "redirect:/login";
    }
}