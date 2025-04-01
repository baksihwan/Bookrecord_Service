package com.example.bookrecordService.global.exception;

import lombok.Getter;

@Getter
public class NotFoundByIdException {

    private final ExceptionType exceptionType;

    public NotFoundByIdException(final ExceptionType exceptionType) {
        this.exceptionType = exceptionType;

    }
}
