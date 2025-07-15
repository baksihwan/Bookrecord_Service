package com.example.bookrecordService.Exception;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public enum ExceptionType {
    COMMENT_NOT_FOUND("댓글을 찾을 수 없습니다."),
    BOARD_NOT_FOUND("보드를 찾을 수 없습니다."),
    USER_NOT_FOUND("유저를 찾을 수 없습니다.");

    private String message;

    ExceptionType(String message) {
        this.message = message;
    }
}
