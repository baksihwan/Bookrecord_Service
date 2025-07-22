package com.example.bookrecordService.domain.User.Service;

import com.example.bookrecordService.Filter.JwtTokenProvider;
import com.example.bookrecordService.domain.User.Dto.*;
import com.example.bookrecordService.domain.User.Entity.User;
import com.example.bookrecordService.domain.User.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;


@Service
@RequiredArgsConstructor
public class UserService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    // 새롭게 알게된점 : 1. 엔티티 안에 필드를 다 호출할  필요가 없다.
    // 2. new User를 기능시키려면 User파일 내에서 불러오려는 파일의 생성자를 만든다.
    // 3. 엔티티는 request, response를 포함한 모든 필드가 있다. 그 중에 필요한 것만 호출하면 된다.

    //  로그인 - 액세스 토큰 발급

    @Transactional
    public Object login(LoginRequestDto request) {
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
        } catch (
                AuthenticationException e) {
            // 인증 실패 시
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new AuthErrorResponse(false, "인증 실패", e.getMessage()));
        }
    }


    @Transactional
    public SignUpResponseDto signUp(String username, String password, Long age) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("유저가 없습니다."));

        User findUser = new User(username, password, age);
        User savedUser = userRepository.save(findUser);
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("비밀번호가 틀렸습니다.");
        } return  SignUpResponseDto.toDto(savedUser);
    }



        @Transactional
        public SignUpResponseDto signUpUser (String username, String password, Long age){

            User user = new User(username, password, age);
            User savedUser = userRepository.save(user);
            if (savedUser == null) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "로그인에 실패하였습니다.");
            }

            return new SignUpResponseDto(savedUser.getId(), savedUser.getUsername(), savedUser.getAge());
        }


        public UserResponseDto findUserById (Long id){
            // 유저 단건조회하는 법(Get)
            User user = userRepository.findByIdOrElseThrow(id); // 1. 유저객체를 예외처리한다.
            return UserResponseDto.toDto(user);   // 2. 이후 유저 객체를 리턴화한다.
        }

        @Transactional
        public void updateUser (Long id, String oldPassword, String newPassword){
            // 유저 수정하는 법(Patch)
            User finduser = userRepository.findByIdOrElseThrow(id);  // 1. 유저객체를 예외처
            if (!finduser.getPassword().equals(oldPassword)) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
            }
            finduser.updatePassword(newPassword);
        }

        @Transactional
        public void deleteUser (Long id){
            // 유저 삭제하는 법(Delete)
            User user = userRepository.findByIdOrElseThrow(id); // 1. 유저 객체를 예외처리
            userRepository.delete(user);  // 2. delete 삭제 기능
        }

    }

