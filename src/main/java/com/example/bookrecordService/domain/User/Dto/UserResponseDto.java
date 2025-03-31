package com.example.bookrecordService.domain.User.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

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
}
