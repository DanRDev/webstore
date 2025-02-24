package com.example.site.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;

@Service
public class JwtService {

    private static final String SECRET_KEY = "your_secret_key";  // Your secret key
    private static final long EXPIRATION_TIME = 86400000L; // 1 day

    // Generate a JWT token
    public String generateToken(String email) {
        Instant now = Instant.now(); // Use Instant for current time
        Instant expiryInstant = now.plusMillis(EXPIRATION_TIME); // Expiration time

        // Convert the secret key to a byte array
        byte[] secretKeyBytes = SECRET_KEY.getBytes(StandardCharsets.UTF_8);

        // Create a SecretKey instance
        SecretKey secretKey = Keys.hmacShaKeyFor(secretKeyBytes);

        return Jwts.builder()
                .subject(email)
                .issuedAt(Date.from(now))
                .expiration(Date.from(expiryInstant))
                .signWith(secretKey) // Use SecretKey for signing
                .compact();
    }

    // Extract email (subject) from the token
    public String extractEmail(String token) {
        return getClaims(token).getSubject();
    }

    // Check if the token is valid (not expired)
    public boolean isValidToken(String token) {
        return getClaims(token).getExpiration().after(new Date());
    }

    private Claims getClaims(String token) {
        // Create a SecretKey instance using the same key used for signing
        byte[] secretKeyBytes = SECRET_KEY.getBytes(StandardCharsets.UTF_8);
        SecretKey secretKey = Keys.hmacShaKeyFor(secretKeyBytes);  // Create a secure key using HMAC

        // Use Jwts.parser() to create the parser and set the signing key
        JwtParser parser = Jwts.parser() // Obtain the JwtParser instance
                .verifyWith(secretKey).build();  // Use the SecretKey for signature verification

        // Now use the parser to parse the claims
        return parser.parseSignedClaims(token).getPayload(); // Parse the token and return claims
    }
}
