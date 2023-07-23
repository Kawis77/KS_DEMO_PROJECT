package com.example.demo.security.service;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class LoginService {


    private static final String SECRET_KEY = "anpulia";

    public  String generateJwtToken(String username) {
        long expirationTimeInMillis = 3600000;

        // Ustawienie daty wygaśnięcia tokenu
        Date expirationDate = new Date(System.currentTimeMillis() + expirationTimeInMillis);

        // Generowanie tokenu JWT
        JwtBuilder builder = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY);

        return builder.compact();
    }

}
