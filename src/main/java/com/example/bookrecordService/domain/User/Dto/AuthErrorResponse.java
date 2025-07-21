package com.example.bookrecordService.domain.User.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthErrorResponse {
    private boolean success;
    private String message;
    private String error;
}