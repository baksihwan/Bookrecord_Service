package com.example.bookrecordService.Filter;

import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.security.SignatureException;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Component
//JWT생성, 유효성 검사 클래스(사용자 인증과 권환 학인)
public class JwtTokenProvider {

    public String createToken(String username, List<String> roles) {
    Claims claims = Jwts.claims().setSubject(username);
    claims.put("roles", roles);

    Date now = new Date();
    Date validity  = new Date(now.getTime() + validityInMilliseconds);

    return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(validity)
            .signWith(SignatureAlgoritm.HS256, secretKey)
            .compact();}
}
