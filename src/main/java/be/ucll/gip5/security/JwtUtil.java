package be.ucll.gip5.security;

import be.ucll.gip5.entity.User;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

@Component
public class JwtUtil {
    private static final int expireTimeInMinutes = 24 * 60 * 60 * 1000;
    @Value("${jwt.token.secret}")
    private String secret;

    public String generate(User user) {
        return Jwts.builder()
                .setSubject(user.getEmail())
                .setIssuer("admin")
                .claim("roles", user.getRole())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expireTimeInMinutes))
                .signWith(SignatureAlgorithm.HS512, Keys.hmacShaKeyFor(secret.getBytes()))
                .compact();
    }

    public boolean validate(String token) {
        if (getUsername(token) != null && isExpired(token)) {
            return true;
        }
        return false;
    }

    public String getUsername(String token) {
        Claims claims = getClaims(token);
        return claims.getSubject();
    }

    private boolean isExpired(String token) {
        Claims claims = getClaims(token);
        return claims.getExpiration().after(new Date(System.currentTimeMillis()));
    }

    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(secret.getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public Claims parseClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }
}
