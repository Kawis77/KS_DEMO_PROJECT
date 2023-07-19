package com.example.demo.users.service;

import com.example.demo.users.dao.entity.UserEntity;
import com.example.demo.users.dao.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserManagerService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserManagerService(UserRepository userRepository, PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(UserEntity userEntity) {
        String encodePassword = passwordEncoder.encode(userEntity.getPassword());
        userEntity.setPassword(encodePassword);
        userEntity.setRole("ROLE_USER");
        userRepository.save(userEntity);
    }
}