package com.example.bookrecordService.domain.User.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserDeleteRequestDto {

    private Long id;
    private String password;
}
