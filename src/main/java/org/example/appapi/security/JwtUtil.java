package org.example.appapi.security;


import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtil {

    private final SecretKey key;
    private final long jwtExpiryMinutes;

    public JwtUtil(@Value("${app.jwt.secret}") String jwtSecret,
                   @Value("${app.jwt.expiry-minutes}") long jwtExpiryMinutes) {
        this.key = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
        this.jwtExpiryMinutes = jwtExpiryMinutes;
    }

    // Generate JWT token
    public String generateToken(String username) {
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + jwtExpiryMinutes * 60_000L))
                .signWith(key)
                .compact();
    }

    // Get username from JWT token
    public String getUsernameFromToken(String token) {
        return Jwts.parser().verifyWith(key).build()
                .parseSignedClaims(token).getPayload().getSubject();
    }

    // Validate JWT token
    public boolean validateJwtToken(String token) {
        try {
            Jwts.parser().verifyWith(key).build().parseSignedClaims(token);
            return true;
        } catch (SecurityException e) {
            // Invalid signature: keep the request unauthenticated.
        } catch (MalformedJwtException e) {
            // Malformed token: keep the request unauthenticated.
        } catch (ExpiredJwtException e) {
            // Expired token: keep the request unauthenticated.
        } catch (UnsupportedJwtException e) {
            // Unsupported token: keep the request unauthenticated.
        } catch (IllegalArgumentException e) {
            // Empty token: keep the request unauthenticated.
        }
        return false;
    }
}
