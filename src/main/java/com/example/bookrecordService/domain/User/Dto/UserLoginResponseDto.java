package com.example.bookrecordService.domain.User.Dto;

import lombok.Getter;

@Getter
public class UserLoginResponseDto {

    private final Long id;

    public UserLoginResponseDto(Long id) {
        this.id = id;
    }
}
