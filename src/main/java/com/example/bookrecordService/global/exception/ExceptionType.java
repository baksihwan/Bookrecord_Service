package com.example.bookrecordService.global.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ExceptionType {
    COMMENT_NOT_FOUND(HttpStatus.BAD_REQUEST, "해당 댓글을 찾을 수 없습니다."),
    USER_NOT_FOUND(HttpStatus.BAD_REQUEST, "해당 유저를 찾을 수 없습니다.")
    FRIEND_NOT_FOUND(HttpStatus.BAD_REQUEST, "해당 친구를 찾을 수 없습니다.")


}
