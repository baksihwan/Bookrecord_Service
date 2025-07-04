package com.example.bookrecordService.Exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class CustomException extends RuntimeException{

    private final ErrorCode errorCode;
}

//각 상황에 맞는 에러코드를 파라미터로 CustomException 던져줌
// 장점 : Exception을 커스텀 하여 작성할 필요가 없어진다.