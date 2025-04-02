package com.example.bookrecordService.domain.Comment.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentRequestDto {
    private Long boardId;
    private  Long userId;
    private String comment;

    public CommentRequestDto(Long boardId, Long userId, String comment) {
        this.boardId = boardId;
        this.userId = userId;
        this.comment = comment;
    }
    }
