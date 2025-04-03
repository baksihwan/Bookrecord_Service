package com.example.bookrecordService.domain.User.Controller;

import com.example.bookrecordService.domain.User.Dto.UserLoginRequestDto;
import com.example.bookrecordService.domain.User.Dto.UserRequestDto;
import com.example.bookrecordService.domain.User.Dto.UserResponseDto;
import com.example.bookrecordService.domain.User.Service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping  // 사용자 전체 조회
    public ResponseEntity<List<UserResponseDto>> findAllUser() {
        List<UserResponseDto> userResponseDto = userService.findAllUser();
        return ResponseEntity.ok(userResponseDto);
    }

    @GetMapping("/info") //사용자 정보 조회 요청 처리
    public String userInfo(HttpServletRequest request,
                           @CookieValue(name = "USER", required = false) String cookie) { //쿠키에서 USER 정보 가져오기
        return userService.userInfo(request, cookie);
    }

    @PostMapping("/login") // 로그인
    public void login(HttpServletResponse httpServletResponse,
                      @RequestBody UserLoginRequestDto requestDto){
        userService.login(httpServletResponse, requestDto);
    }

    @PatchMapping("/{id}") // 사용자 정보 수정
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable Long id){
        UserResponseDto userResponseDto = userService.updateUser(id);
        return ResponseEntity.ok(userResponseDto);
    }

    @DeleteMapping("/{id}") // 사용자 삭제
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
