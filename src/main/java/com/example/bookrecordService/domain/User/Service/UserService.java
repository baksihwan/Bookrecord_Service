package com.example.bookrecordService.domain.User.Service;

import com.example.bookrecordService.domain.User.Dto.UserRequestDto;
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

    public UserResponseDto findByIdUser(Long id) {
        // 유저 단건조회하는 법(Get)
        User user = userRepository.findByIdOrElseThrow(id);   // 1. 유저객체를 예외처리한다.
        return UserResponseDto.toDto(user);   // 2. 이후 유저 객체를 리턴화한다.
    }

    public UserResponseDto saveUser(UserRequestDto requestDto) {
        // 유저 생성하는 법(Post)
        User user = new User(requestDto);  // 1. 유저 객체를 만든다.
        User savedUser = userRepository.save(user); // 2. 유저 객체를 저장한다.
        return UserResponseDto.toDto(savedUser);
    }
}
