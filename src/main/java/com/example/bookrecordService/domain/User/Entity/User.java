package com.example.bookrecordService.domain.User.Entity;

import com.example.bookrecordService.global.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
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
    @Column(name = "username")
    private String username;

    @NotBlank
    @Column(name = "age")
    private Long age;


    @NotBlank
    @Column(name = "password")
    private String password;

    @NotBlank
    @Column(name = "user_nickname")
    private String userNickname;

    @NotBlank
    @Column(name = "phone_number")
    private Long phoneNumber;

    @Column(name = "role")
    private String role;


    public void updatePassword(String password) {
        this.password = password;
    }

    public User(String username, String password, Long age) {
        this.username = username;
        this.password = password;
        this.age = age;
    }
}

