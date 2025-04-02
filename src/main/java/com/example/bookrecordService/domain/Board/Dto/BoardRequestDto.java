package com.example.bookrecordService.domain.Board.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardRequestDto {
    private Long userId;
    private String title;
    private String content;

    public BoardRequestDto(Long userId, String title, String content) {
        this.userId = userId;
        this.title = title;
        this.content = content;
    }
}
