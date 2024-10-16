package com.smarty.system.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

@Service
public class JwtUtils {
    private String jwtSigningKey = "secret";
    public String extractUsername(String token) {return extractClaim(token, Claims::getSubject); }
    public Date extractExpriration(String token) {return extractClaim(token, Claims::getExpiration); }

    public boolean hasClaim(String token, String claimName) {
        final Claims claims = extractAllClaims(token);
        return claims.get(claimName) != null;
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsReslover) {
        final Claims claims = extractAllClaims(token);
        return claimsReslover.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(jwtSigningKey).parseClaimsJwt(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpriration(token).before(new Date());
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails);
    }

    public String generateToken(UserDetails userDetails, Map<String, Object> claims) {
        return createToken(claims, userDetails);
    }

    private String createToken(Map<String, Object> claims, UserDetails userDetails) {
        return Jwts.builder().setClaims(claims)
                .setSubject(userDetails.getUsername())
                .claim("authorities",userDetails.getAuthorities())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TimeUnit.HOURS.toMillis(200)))
                .signWith(SignatureAlgorithm.HS256, jwtSigningKey).compact();
    }
    public Boolean validateToken(String token,UserDetails userDetails) {
        final String username= extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));

    }

}
