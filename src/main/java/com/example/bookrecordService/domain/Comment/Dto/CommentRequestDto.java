package com.example.bookrecordService.domain.Comment.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentRequestDto {
    private Long friendId;
    private  Long userId;
    private String comment;

    }
