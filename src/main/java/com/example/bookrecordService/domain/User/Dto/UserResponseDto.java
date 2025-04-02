package com.example.bookrecordService.domain.User.Dto;

import com.example.bookrecordService.domain.Comment.Dto.CommentResponseDto;
import com.example.bookrecordService.domain.Comment.Entity.Comment;
import com.example.bookrecordService.domain.Image.Entity.Image;
import com.example.bookrecordService.domain.User.Entity.User;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class UserResponseDto {
    private Long id;
    private String email;
    private String userNickname;
    private Long phoneNumber;
    private LocalDateTime createdAt;
    private LocalDateTime modifyAt;

    public UserResponseDto(Long id, String email, String userNickname, Long phoneNumber, LocalDateTime createdAt, LocalDateTime modifyAt) {
        this.id = id;
        this.email = email;
        this.userNickname = userNickname;
        this.phoneNumber = phoneNumber;
        this.createdAt = createdAt;
        this.modifyAt = modifyAt;
    }

    public static UserResponseDto toDto(User user) {
        return new UserResponseDto(
                user.getId(),
                user.getEmail(),
                user.getUserNickname(),
                user.getPhoneNumber(),
                user.getCreatedAt(),
                user.getModifiedAt()
        );
    }

    public static List<UserResponseDto> toDto(List<User> users) {
        return users.stream().map(UserResponseDto::toDto).collect(Collectors.toList());
    }
}
