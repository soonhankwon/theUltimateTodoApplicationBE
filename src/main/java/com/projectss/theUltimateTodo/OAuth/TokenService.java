package com.projectss.theUltimateTodo.OAuth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Slf4j
@Service
@RequiredArgsConstructor
public class TokenService {

    @Value("${jwt.secret}")
    private String secretKey;
    private static final Long ACCESS_TOKEN_EXPIRED_TIME_MS = 1000 * 60 * 60L;


    public String generateToken(String userId,String nickname,String profileImage, String thumbnailImage,String email) {
        log.info("secret key : {}",secretKey);

        Claims claims = Jwts.claims(); //일종의 map
        claims.put("id",userId);
        claims.put("auth", "ROLE_USER");
        claims.put("nickname", nickname);
        claims.put("profileImage", profileImage);
        claims.put("thumbnailImage", thumbnailImage);
        claims.put("email", email);
        log.info("claims : {}",claims);


        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRED_TIME_MS))
                .signWith(SignatureAlgorithm.HS256, secretKey.getBytes())
                .compact();
    }


    // Request Header 에서 토큰 정보를 꺼내오기
    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public boolean isTokenValid(String token) {
        return Jwts.parser().setSigningKey(secretKey.getBytes()).parseClaimsJws(token)
                .getBody().getExpiration().after(new Date());
    }

    public  String getUserIdFromToken(String token) {
        return Jwts.parser().setSigningKey(secretKey.getBytes()).parseClaimsJws(token)
                .getBody().get("id", String.class);
    }
}
