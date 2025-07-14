package com.example.bookrecordService.Exception;


public class NotFoundByIdException extends RuntimeException {

    private final ExceptionType exceptionType;

    public NotFoundByIdException(ExceptionType exceptiontype) {
        super(exceptiontype.getMessage());
        this.exceptionType = exceptiontype;
    }

    public ExceptionType getExceptionType() {
        return exceptionType;
    }
}
