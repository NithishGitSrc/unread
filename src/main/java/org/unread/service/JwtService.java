package org.unread.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;

@Service
public class JwtService {

    private static final String SECRET = "mysecretkeymysecretkeymysecretkeymy";

    public String generateToken(String mailId) {

        return Jwts.builder()
                .subject(mailId)
                .addClaims(new HashMap<>())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
                .signWith(signedKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateToken(String mailId, Long userId) {
        HashMap<String, Object> claims = new HashMap<>();
        claims.put("userId", userId);
        return Jwts.builder()
                .subject(mailId)
                .addClaims(claims)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
                .signWith(signedKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    Key signedKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
    }

    public String getMailIdFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(signedKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public Long getUserIdFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims.get("userId", Long.class);
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(signedKey())
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Claims getClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(signedKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean isTokenExpired(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims.getExpiration().before(new Date());
    }

}
