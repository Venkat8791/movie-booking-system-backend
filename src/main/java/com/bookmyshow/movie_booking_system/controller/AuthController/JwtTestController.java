package com.bookmyshow.movie_booking_system.controller.AuthController;

import com.bookmyshow.movie_booking_system.entity.mysql.User;
import com.bookmyshow.movie_booking_system.service.auth.JwtService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/test-jwt")
public class JwtTestController {

    @Autowired
    JwtService jwtService;

    @GetMapping("/generate")
    public String generateToken(@RequestParam("phoneNumber") String phoneNumber){
        User user = new User();
        user.setId(1L);
        user.setPhoneNumber(phoneNumber);
        return jwtService.generateToken(user);
    }

    @GetMapping("/validate")
    public String validateToken(@RequestBody String token) {
        try {
            Claims claims = jwtService.extractClaims(token);
            return "Token is valid. Username: " + claims.getSubject();
        } catch (Exception e) {
            return "Invalid token: " + e.getMessage();
        }
    }


}
