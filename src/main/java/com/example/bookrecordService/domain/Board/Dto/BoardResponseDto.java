package com.example.bookrecordService.domain.Board.Dto;

import com.example.bookrecordService.domain.Board.Entity.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardResponseDto {
    private Long id;
    private String title;
    private String contents;

    public BoardResponseDto(Long id, String title, String contents) {
        this.id = id;
        this.title = title;
        this.contents = contents;
    }

    public static BoardResponseDto toDto(Board board) {
        return new BoardResponseDto(board.getId(),
                                    board.getTitle(),
                                    board.getContents());
    }
}

