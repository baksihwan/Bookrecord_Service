package com.example.bookrecordService.Filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import java.util.*;


@Slf4j
@Component
//JWT생성, 유효성 검사 클래스(사용자 인증과 권환 학인)
public class JwtTokenProvider {

    @Value("${jwt.secret}") // 환경 변수 주입
    private String secretKey;

    @Value("${jwt.expiration}")
    private Long validityInMilliseconds;

    private final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

    //토큰 유효성 검사
    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            log.info("exception : {}", e.getMessage());
            return false;
        }
    }

   //토큰에서 username 추출
    public String getUsername(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey.getBytes())
                .parseClaimsJws(token)
                .getBody()
                .getSubject(); // Subject에 username 저장
    }

    public String resolveToken(HttpServletRequest request) { // 요청 header에서 jwt 토큰 추출메서드
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public String createToken(Authentication authentication) {
    Claims claims = Jwts.claims().setSubject(authentication.getName());

    Date now = new Date();
    Date validity  = new Date(now.getTime() + validityInMilliseconds);

    return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(validity)
            .signWith(signatureAlgorithm, secretKey)
            .compact();}

    public Authentication getAuthentication(String token){
        String username = getUsername(token);
        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_USER"));

        return new UsernamePasswordAuthenticationToken(username, token, authorities);



    }


}
