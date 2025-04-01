package com.example.bookrecordService.domain.Board.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardRequestDto {
    private Long userId;
    private String title;
    private String content;
}
