package com.example.bookrecordService.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NotFoundByIdException extends RuntimeException {

    private final ExceptionType exceptionType;
}
