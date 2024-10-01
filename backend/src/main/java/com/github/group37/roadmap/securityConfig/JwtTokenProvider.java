package com.github.group37.roadmap.securityConfig;

import java.util.ArrayList;
import java.util.Date;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {
    private static final long EXPIRATION_TIME = 864_000_00; // 1 day in milliseconds
    private static final String SIGNING_KEY = "9a4f2c8d3b7a1e6f45c8a0b3f267d8b1d4e6f3c8a9d2b5f8e3a9c8b5f6v8a3d9";
    private static final String PREFIX = "Bearer ";
    private static final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS512;
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenProvider.class);

    public String generateToken(String email) {
        SecretKey key = Keys.hmacShaKeyFor(SIGNING_KEY.getBytes());

        String jwtToken = Jwts.builder()
                .setSubject(email)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key, SIGNATURE_ALGORITHM)
                .compact();

        System.out.println("Generated Token: " + jwtToken);
        return jwtToken;
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith(PREFIX)) {
            return bearerToken.substring(PREFIX.length());
        }
        return null;
    }

    public Authentication getAuthentication(String token) {
        SecretKey key = Keys.hmacShaKeyFor(SIGNING_KEY.getBytes());
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        String email = claims.getSubject();

        if (email != null) {
            return new UsernamePasswordAuthenticationToken(email, null, new ArrayList<>());
        }
        return null;
    }

    public boolean validateToken(String token) {
        try {
            SecretKey key = Keys.hmacShaKeyFor(SIGNING_KEY.getBytes());
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            LOGGER.error("Error validating JWT token: {}", e.getMessage());
        }
        return false;
    }


}

