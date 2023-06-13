package com.example.onlinemarket.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtProvider {

    private static final String secretKey = "SoftwareEngineer";
    private static final int expiredTime = 1000 * 60 * 60;// 1 hour

    public String generateToken(String email) {
        return Jwts
                .builder()
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiredTime))
                .compact();
    }

    public String validateToken(String token) {
        try {
            return Jwts
                    .parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (Exception e) {
            return null;
        }
    }

}
