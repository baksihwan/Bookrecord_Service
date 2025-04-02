package com.example.bookrecordService.domain.User.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@Getter
public class UserRequestDto {
    private String email;
    private String password;
    private String name;
    private Long phoneNumber;
    private MultipartFile profileImage;

    public UserRequestDto(String email, String password, String name, Long phoneNumber, MultipartFile profileImage) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.profileImage = profileImage;
    }


}
