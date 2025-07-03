package com.example.bookrecordService.Exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {    // 클래스에서 HttpStauts까지 관리하도록 변경
    LOGIN_DENIED(HttpStatus.UNAUTHORIZED, "로그인이 실패했습니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버에 예상치 못한 에러가 발생했습니다.")

    private final HttpStatus httpStatus;
    private final String message;

}