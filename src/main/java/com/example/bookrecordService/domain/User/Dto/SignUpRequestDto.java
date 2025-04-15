package com.example.bookrecordService.domain.User.Dto;

import lombok.Getter;

@Getter
public class SignUpRequestDto {

    private final String username;
    private final String password;
    private final Long age;

    public SignUpRequestDto(String username, String password, Long age) {
        this.username = username;
        this.password = password;
        this.age = age;
    }
}
