package com.example.bookrecordService.domain.User.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginJwtTokenDto {

    String username;
    String grantType;
    String accessToken;
    Long accessTokenExpiresAt;


    public LoginJwtTokenDto(String username) {
        this.username = username;
    }
}
