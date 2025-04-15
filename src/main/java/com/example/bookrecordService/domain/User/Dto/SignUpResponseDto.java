package com.example.bookrecordService.domain.User.Dto;

import lombok.Getter;

@Getter
public class SignUpResponseDto {
    private final Long id;
    private final String username;
    private final Long age;

    public SignUpResponseDto(Long id, String username, Long age) {
        this.id = id;
        this.username = username;
        this.age = age;
    }
}
