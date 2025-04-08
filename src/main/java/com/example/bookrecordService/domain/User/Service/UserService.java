package com.example.bookrecordService.domain.User.Service;

import com.example.bookrecordService.domain.User.Dto.UserLoginRequestDto;
import com.example.bookrecordService.domain.User.Dto.UserLoginResponseDto;
import com.example.bookrecordService.domain.User.Dto.UserResponseDto;
import com.example.bookrecordService.domain.User.Entity.User;
import com.example.bookrecordService.domain.User.Repository.UserRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Slf4j // 로깅을 위한 어노테이션
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
    // 로그인 처리 매서드
    public UserLoginResponseDto login(UserLoginRequestDto requestDto, HttpServletRequest request) {
        var email = requestDto.getEmail(); // 로그인 시 입력된 이메일
        var password = requestDto.getPassword(); // 로그인 시 입력된 비밀번호

        // 이메일로 사용자 찾기
        var optionalUser = userRepository.findByEmail(email);

        // 이메일이 등록되지 않은 경우 예외 발생
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("해당 이메일은 가입되지 않았습니다.");
        }

        var user = optionalUser.get(); // 사용자가 존재하는 경우

        //비밀번호 확인
        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("비밀번호가 맞지 않습니다.");
        }

        request.getSession().setAttribute("USER", user);
        return new UserLoginResponseDto(optionalUser.getId());

    }

    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
    }

    //사용자 정보 조회 메서드
    public String userInfo(HttpServletRequest httpServletRequest, String cookie){
        //쿠키가 업는 경우 로그인 요청
        if(cookie == null){
            return "로그인을 먼저 해주세요.";
        }

        //쿠키로 사용자 찾기
        var optionalUser = userRepository.findById(cookie);
        if(optionalUser.isEmpty()){
            return "다시 로그인을 해주세요.";
        }

        //자동 로그인 성공
        var user = optionalUser.get();
        return user.toString();





    }



    public UserResponseDto updateUser(Long id){
        // 유저 수정하는 법(Patch)
        User user = userRepository.findByIdOrElseThrow(id);  // 1. 유저객체를 예외처리한다.
        User saveUser = userRepository.save(user);         // 2. 유저 객체를 저장한다.
        return UserResponseDto.toDto(saveUser);
    }

    public void deleteUser(Long id){
        // 유저 삭제하는 법(Delete)
        User user = userRepository.findByIdOrElseThrow(id); // 1. 유저 객체를 예외처리
        userRepository.delete(user);  // 2. delete 삭제 기능
    }


