package com.example.bookrecordService.domain.Comment.Dto;

import com.example.bookrecordService.domain.Comment.Entity.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
public class CommentResponseDto {
    private Long id;
    private Long userId;
    private String comment;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public CommentResponseDto(Long id, Long userId, String comment, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.userId = userId;
        this.comment = comment;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public static CommentResponseDto toDto(Comment comment){
        return new CommentResponseDto(
                comment.getId(),
                comment.getUser().getId(),
                comment.getComment(),
                comment.getCreatedAt(),
                comment.getModifiedAt());
    }

    public static List<CommentResponseDto> toDto(List<Comment> comments){
        return comments.stream().map(CommentResponseDto::toDto).collect(Collectors.toList());
    }
}
