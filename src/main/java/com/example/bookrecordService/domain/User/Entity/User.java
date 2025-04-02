package com.example.bookrecordService.domain.User.Entity;

import com.example.bookrecordService.domain.Image.Entity.Image;
import com.example.bookrecordService.global.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Entity
@Getter
@Table(name = "user")
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "email", unique = true)
    private String email;

    @NotBlank
    @Column(name = "password")
    private String password;

    @NotBlank
    @Column(name = "user_nickname")
    private String userNickname;

    @NotBlank
    @Column(name = "phone_number")
    private Long phoneNumber;


}

