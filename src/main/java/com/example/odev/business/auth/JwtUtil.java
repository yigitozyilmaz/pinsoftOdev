package com.example.odev.business.auth;

import com.example.odev.Entity.User;
import io.jsonwebtoken.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import javax.naming.AuthenticationException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class JwtUtil {
    private final String secretKey = "mysecretkey";
    private long accesTokenValidity = 60*60*1000;
    private final JwtParser jwtParser;
    private final String tokenHeader = "Authorization";
    private final String tokenPrefix = "Bearer ";

    public JwtUtil() {
        this.jwtParser = Jwts.parser().setSigningKey(secretKey);
    }

    public String createToken (User user) {
        Claims claims = Jwts.claims().setSubject(user.getUsername());
        Date tokenCreateTime = new Date();
        Date tokenValidity = new Date(tokenCreateTime.getTime() + TimeUnit.MINUTES.toMillis(accesTokenValidity));

        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(tokenValidity)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    private Claims parseJwtClaims (String token) {
        return jwtParser.parseClaimsJwt(token).getBody();
    }

    public Claims resolveClaims (HttpServletRequest request1) {
        try {
            String token = resolveToken(request1);
            if (token != null) {
                return parseJwtClaims(token);
            }
            return null;
        } catch (ExpiredJwtException exception1) {
            request1.setAttribute("Süresi Bitmiş !", exception1.getMessage());
            throw exception1;
        } catch (Exception exception2) {
            request1.setAttribute("Geçersiz !", exception2.getMessage());
            throw exception2;
        }
    }

    public String resolveToken (HttpServletRequest request2) {
        String bearerToken = request2.getHeader(tokenHeader);
        if (bearerToken != null && bearerToken.startsWith(tokenPrefix)) {
            return bearerToken.substring(tokenPrefix.length());
        } return null;
    }

    public boolean validateClaims (Claims claims) throws AuthenticationException {
        try {
            return claims.getExpiration().after(new Date());
        } catch (Exception exception3) {
            return true;
        }
    }

    public String getUsername (Claims claims) {
        return claims.getSubject();
    }

    private List<String> getAuthRoles (Claims claims) {
        return (List<String>) claims.get("authRoles");
    }

}
