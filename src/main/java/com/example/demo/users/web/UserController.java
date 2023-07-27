package com.example.demo.users.web;

import com.example.demo.users.dao.entity.UserEntity;
import com.example.demo.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;


    @GetMapping("/all/users")
    public ResponseEntity<List<UserEntity>> getAllUsers(){

        List<UserEntity> userEntityList = userService.readAll();

        if (userEntityList != null) {
            return new ResponseEntity<>(userEntityList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
