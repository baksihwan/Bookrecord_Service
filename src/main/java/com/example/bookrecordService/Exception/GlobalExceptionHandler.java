package com.example.bookrecordService.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handleLoginDeniedException(CustomException e) {
        return handleExceptionInternal(e.getErrorCode());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(CustomException e){
        return handleExceptionInternal(ErrorCode.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<ErrorResponse> handleExceptionInternal(ErrorCode errorCode){
        ErrorResponse errorResponse = new ErrorResponse(errorCode.getMessage());
        return  ResponseEntity.status(errorCode.getHttpStatus()).body(errorResponse);


    }

}
