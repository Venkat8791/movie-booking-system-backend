package com.bookmyshow.movie_booking_system.service.auth;


import com.bookmyshow.movie_booking_system.entity.mysql.User;
import io.github.cdimascio.dotenv.Dotenv;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtService {

    private final Dotenv dotenv = Dotenv.load();

    private String secretKey;

    private Key key;

    public JwtService() {
    }

    @PostConstruct
    public void initJwtService() {
        secretKey = dotenv.get("JWT_SECRET_KEY");
        key = Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    public String extractPhoneNumber(String token) {
        return extractClaims(token).getSubject();
    }

    public String generateToken(User user) {
        return Jwts.builder().setSubject(user.getId().toString()).claim("phoneNumber", user.getPhoneNumber())
                .setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .signWith(key).compact();
    }

    public Claims extractClaims(String token) {
        try {
            return Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token).getBody();
        } catch (Exception e) {
            throw new Error("Unable to decrypt the token: " + e.getMessage());
        }
    }

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }
}
