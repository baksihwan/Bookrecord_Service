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


}
