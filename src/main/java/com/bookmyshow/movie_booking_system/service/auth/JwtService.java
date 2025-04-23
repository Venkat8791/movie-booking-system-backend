package com.bookmyshow.movie_booking_system.service.auth;


import com.bookmyshow.movie_booking_system.entity.mysql.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtService {


    private final String secretKey = "77f889df60caad04c790e32db416332c6318acfff8b02901a22c9b10fec2c7d7fe865dcc5ec6ad5102385f8243b8697a81a3510e4bee696ae63c6068a5131ca6a9e43a7ebfe0a3113a3a21997d6e5ff68182cbf1a7a3f8b9e6e3fa2dcf220f0951197400e5c2ecaba8214a97a973f7f9aad31ac641ee44727b9325ebcf1c6826a687e0c77e1af9f9292893ab3a94ec1103ef3bb72961f0bc4cda7b378986e5a94862d8726e202a0bda8e5ca3481c3bb2aacb452e40b75d32bfb5ec372336b29895057a7c6f178e63487ac70a71e5995d47b3ae650329e20a23a5357603521a34c644e46e17fa7fda6dc98e37784ec50f15d8ef17d49d05a063e8ca4dfe6e3001";

    private final Key key = Keys.hmacShaKeyFor(secretKey.getBytes());

    public JwtService() {

    }

    public String extractPhoneNumber(String token) {
        return extractClaims(token).getSubject();
    }

    public String generateToken(User user){
        return Jwts.builder().setSubject(user.getId().toString()).claim("phoneNumber",user.getPhoneNumber())
                .setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis() + 1000*60*60*24))
                .signWith(key).compact();
    }

    public Claims extractClaims(String token){
        try{
            return Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token).getBody();
        }catch(Exception e){
            throw new Error("Unable to decrypt the token: " + e.getMessage());
        }
    }

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }
}
