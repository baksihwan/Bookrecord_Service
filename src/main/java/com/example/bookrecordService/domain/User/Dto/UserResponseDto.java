package com.example.bookrecordService.domain.User.Dto;

import com.example.bookrecordService.domain.Comment.Dto.CommentResponseDto;
import com.example.bookrecordService.domain.Comment.Entity.Comment;
import com.example.bookrecordService.domain.User.Entity.User;
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
    private String password;
    private String name;
    private Long phoneNumber;
    private MultipartFile profileImage;
    private LocalDateTime createdAt;
    private LocalDateTime modifyAt;

    public static UserResponseDto toDto(User user){
        return new UserResponseDto(user.getId(),
                                   user.getUsername()
        )
    }

    public static List<UserResponseDto> toDto(List<User> users) {
        return users.stream().map(UserResponseDto::toDto).collect(Collectors.toList());
    }
}
