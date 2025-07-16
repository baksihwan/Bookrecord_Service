package com.example.bookrecordService.domain.User.Controller;
import com.example.bookrecordService.domain.User.Dto.*;
import com.example.bookrecordService.domain.User.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<LoginJwtTokenDto> login(@RequestBody LoginRequestDto request) {
        String token = userService.login(request.getUsername(), request.getPassword());
        return ResponseEntity.ok(new LoginJwtTokenDto(token));
    }


    @PostMapping
    public ResponseEntity<SignUpResponseDto> signUpUser(@RequestBody SignUpRequestDto requestDto){
        SignUpResponseDto signUpResponseDto = userService.signUpUser(requestDto.getUsername(), requestDto.getPassword(),
                                                                   requestDto.getAge());
        return ResponseEntity.ok(signUpResponseDto);
    }


    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findUserById(@PathVariable Long id){
        UserResponseDto userResponseDto = userService.findUserById(id);
        return ResponseEntity.ok(userResponseDto);
    }

    @PatchMapping("/{id}") // 사용자 정보 수정
    public ResponseEntity<Void> updateUser(@PathVariable Long id,
                                                      @RequestBody UpdatePasswordRequestDto requestDto) {
        userService.updateUser(id, requestDto.getOldPassword(), requestDto.getNewPassword());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}") // 사용자 삭제
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
