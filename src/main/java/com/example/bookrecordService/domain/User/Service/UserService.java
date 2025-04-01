package com.example.bookrecordService.domain.User.Service;

import com.example.bookrecordService.domain.User.Dto.UserResponseDto;
import com.example.bookrecordService.domain.User.Entity.User;
import com.example.bookrecordService.domain.User.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<UserResponseDto> findAllUser(){
        // 유저 전체조회하는 법(Get)
        List<User> users = new ArrayList<>(); // 1. 유저를 리스트화한다.
        return UserResponseDto.toDto(users);  // 2. 유저를 스트링 기법으로 리스트화한다.

    }
}
