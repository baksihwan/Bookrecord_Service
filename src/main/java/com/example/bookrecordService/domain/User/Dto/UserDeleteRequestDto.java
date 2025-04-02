package com.example.bookrecordService.domain.User.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserDeleteRequestDto {
    private String password;

    public UserDeleteRequestDto(String password) {
        this.password = password;
    }
}
