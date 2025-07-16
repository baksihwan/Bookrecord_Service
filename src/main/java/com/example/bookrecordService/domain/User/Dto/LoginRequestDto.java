package com.example.bookrecordService.domain.User.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginRequestDto {

    private String username;
    private String password;
}
