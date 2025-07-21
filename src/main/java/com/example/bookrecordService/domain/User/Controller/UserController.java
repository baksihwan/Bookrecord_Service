package com.example.bookrecordService.domain.User.Controller;
import com.example.bookrecordService.Filter.JwtTokenProvider;
import com.example.bookrecordService.domain.User.Dto.*;

import com.example.bookrecordService.domain.User.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );

            // 인증 성공 시 jwt 생성

            SecurityContextHolder.getContext().setAuthentication(authentication); // 인증 정보 저장

            String token = jwtTokenProvider.createToken(authentication); // JWT 생성

            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Bearer " + token);


            return ResponseEntity.ok().headers(headers).body(new LoginJwtTokenDto(request.getUsername()));
        } catch (AuthenticationException e) {
            // 인증 실패 시
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new AuthErrorResponse(false, "인증 실패", e.getMessage()));
        }
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
