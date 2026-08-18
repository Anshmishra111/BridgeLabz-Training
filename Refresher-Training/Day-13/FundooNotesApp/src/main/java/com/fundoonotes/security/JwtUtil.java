package com.fundoonotes.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * Utility class for generating and parsing JWT tokens.
 *
 * <p>The token contains the userId as subject and email as a custom claim.
 * Expiration is configurable via application.properties (jwt.expirationMillis).
 * For Problem 4, temporarily set jwt.expirationMillis=5000 to test expired tokens.</p>
 */
@Component
public class JwtUtil {

    private final SecretKey key;
    private long expirationMillis;

    public JwtUtil(
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.expirationMillis}") long expirationMillis) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.expirationMillis = expirationMillis;
    }

    /**
     * Generates a signed JWT token for a given user.
     *
     * @param userId the user's ID (becomes the token subject)
     * @param email  the user's email (stored as a custom claim)
     * @return the compact JWT string
     */
    public String generateToken(String userId, String email) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + expirationMillis);

        return Jwts.builder()
                .subject(userId)
                .claim("email", email)
                .issuedAt(now)
                .expiration(expiry)
                .signWith(key)
                .compact();
    }

    /**
     * Parses and validates a JWT token, returning the claims.
     * Throws ExpiredJwtException if the token has expired,
     * or other JwtException subtypes for invalid tokens.
     *
     * @param token the compact JWT string
     * @return the verified claims
     */
    public Claims parseToken(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    /**
     * Extracts the userId (subject) from a token.
     */
    public String getUserIdFromToken(String token) {
        return parseToken(token).getSubject();
    }

    /**
     * Extracts the email claim from a token.
     */
    public String getEmailFromToken(String token) {
        return parseToken(token).get("email", String.class);
    }

    /**
     * Allows overriding expiration at runtime (useful for Problem 4 testing).
     */
    public void setExpirationMillis(long expirationMillis) {
        this.expirationMillis = expirationMillis;
    }
}
