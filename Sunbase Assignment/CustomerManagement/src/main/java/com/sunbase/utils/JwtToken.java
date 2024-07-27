package com.sunbase.utils;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.context.annotation.Role;

import com.sunbase.model.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JwtToken {
	
	public static String generateToken(User user) {
        SecretKey key = Keys.hmacShaKeyFor(SecurityDetails.JWT_KEY.getBytes());
        return Jwts.builder()
                .setIssuer("YourAppName")
                .setSubject("JWT_Token")
                .claim("username", user.getEmail())
//                .claim("authorities", getAuthorities(user.getEmail())) // Adjust based on roles
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + 86400000)) // 1 day expiration
                .signWith(key).compact();
    }

    // Convert role to a string representation for JWT claims
//    private static String getAuthorities(Role role) {
//        return role; // Use name() method for enum
//    }

}
