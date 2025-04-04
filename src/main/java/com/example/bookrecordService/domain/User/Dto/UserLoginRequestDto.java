package com.example.bookrecordService.domain.User.Dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
public class UserLoginRequestDto {

    private final String email;
    private final String password;

    public UserLoginRequestDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
