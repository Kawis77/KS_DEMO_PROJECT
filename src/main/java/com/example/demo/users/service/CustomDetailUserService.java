package com.example.demo.users.service;


import com.example.demo.users.dao.entity.UserEntity;
import com.example.demo.users.dao.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
    public class CustomDetailUserService implements UserDetailsService {
        private final UserRepository userRepository;


        public CustomDetailUserService(UserRepository userRepository) {
            this.userRepository = userRepository;
        }

        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

            Optional<UserEntity> optionalUser = userRepository.findByUsername(username);
            return optionalUser
                    .map(user -> new org.springframework.security.core.userdetails.User(
                            user.getUsername(),
                            user.getPassword(),
                            List.of(new SimpleGrantedAuthority(user.getRole()))
                    ))
                    .orElseThrow(() -> new UsernameNotFoundException("Username" + username + "Not found"));

        }
    }

