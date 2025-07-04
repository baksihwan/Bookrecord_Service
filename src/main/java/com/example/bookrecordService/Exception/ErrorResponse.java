package com.example.bookrecordService.Exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ErrorResponse {
    // Http 상태 코드와 에러 메세지를 응답하는 클래스
    private final String message;

    }

