package com.edoras.backend.security;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;

@Slf4j
@Component
public class JwtTokenUtility {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expirationInMs}")
    private String jwtExpirationInMs;

    @Autowired
    MyUserDetailsService myUserDetailsService;

    public String generateToken(UserDetails userDetails) {

        Date now = new Date();
        Instant expirationDate = Instant.ofEpochMilli(now.getTime() + Long.parseLong(jwtExpirationInMs));

        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(now)
                .setExpiration(Date.from(expirationDate))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public boolean validateToken(String jwtToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(jwtToken).getBody();
            return true;
        } catch (SignatureException ex) {
            log.error("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            log.error("Illegal JWT claim");
        }
        return false;
    }

    public String getUserNameFromToken(String jwtToken) {
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(jwtToken)
                .getBody()
                .getSubject();
    }

}
