package com.example.bookrecordService.domain.Board.Dto;

import com.example.bookrecordService.domain.User.Entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardResponseDto {

    private Long id;
    private String title;
    private String contents;
    private String userNickname;

    public BoardResponseDto toDto(User user) {
        return new BoardResponseDto(user.get)

    }
}
