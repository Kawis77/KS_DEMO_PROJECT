package com.example.demo.users.service;

import com.example.demo.users.dao.entity.UserEntity;
import com.example.demo.users.dao.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;


    public List<UserEntity> readAll() {
        List<UserEntity> userEntityList = userRepository.findAll();
        return userEntityList;
    }

    public Optional<UserEntity> getUserById(Long id) {
        if (id != null) {
            return userRepository.findById(id);
        }
        return Optional.empty();
    }

}
