package com.example.bookrecordService.Exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handleLoginDeniedException(CustomException e) {
        return handleExceptionInternal(e.getErrorCode());
    } //handlerExceptionInternal 뜻: 예외 발생시 스프링 내부에서 예외처리하도록 하는 메서드

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(CustomException e){
        return handleExceptionInternal(ErrorCode.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<ErrorResponse> handleExceptionInternal(ErrorCode errorCode){
        ErrorResponse errorResponse = new ErrorResponse(errorCode.getMessage());
        return  ResponseEntity.status(errorCode.getHttpStatus()).body(errorResponse);


    }

}
