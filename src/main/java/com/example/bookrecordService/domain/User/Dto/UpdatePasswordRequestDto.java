package com.example.bookrecordService.domain.User.Dto;

import lombok.Getter;

@Getter
public class UpdatePasswordRequestDto {

    private final String oldPassword;
    private final String newPassword;

    public UpdatePasswordRequestDto(String oldPassword, String newPassword) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }
}
