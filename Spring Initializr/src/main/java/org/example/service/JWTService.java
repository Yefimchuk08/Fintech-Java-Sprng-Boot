package org.example.crudapp.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {

    private final String SECRET_KEY = "mySecretKey123456789";

    public String generateToken(String email) {

        return Jwts.builder()
                .setSubject(email) // кого токен
                .setIssuedAt(new Date()) // коли створений
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 година
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }
}
