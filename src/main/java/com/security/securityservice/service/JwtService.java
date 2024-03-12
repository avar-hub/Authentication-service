package com.security.securityservice.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
@Slf4j
public class JwtService {

    private static final String SECRET= "3e2f7603853edc2b696f88e2a9a3c523d2dfddcb4fbecd869bf0e31817daf24e";

    public void validateToken(String token) {
       Jwts.parserBuilder()
               .setSigningKey(getSigningKey())
                .build()
               .parseClaimsJws(token);

    }
    public String generateToken(String username) {

        Map<String, Objects> claims= new HashMap<>();
        return createToken(claims,username);
    }

    public String createToken(Map<String,Objects> claims, String username) {
        String jwtToken = Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+ 1000*60*30))
                .signWith(getSigningKey())
                .compact();
        log.info("JWT token --->  {}",jwtToken);
        return jwtToken;
    }

    public SecretKey getSigningKey() {

        byte[] keyBytes= Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }


}
