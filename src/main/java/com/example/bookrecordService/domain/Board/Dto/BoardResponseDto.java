package com.example.bookrecordService.domain.Board.Dto;

import com.example.bookrecordService.domain.Board.Entity.Board;
import com.example.bookrecordService.domain.Comment.Dto.CommentResponseDto;
import com.example.bookrecordService.domain.Comment.Entity.Comment;
import com.example.bookrecordService.domain.User.Entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class BoardResponseDto {

    private Long id;
    private String title;
    private String contents;
    private String userNickname;

    public BoardResponseDto toDto(Board board) {
        return new BoardResponseDto(board.getId(),
                                    board.getTitle());
    }

    public static List<BoardResponseDto> toDto(List<Board> boards) {
        return boards.stream().map(CommentResponseDto::toDto).collect(Collectors.toList());
    }
}
