package com.example.bookrecordService.domain.User.Service;

import com.example.bookrecordService.domain.User.Dto.SignUpResponseDto;
import com.example.bookrecordService.domain.User.Dto.UserResponseDto;
import com.example.bookrecordService.domain.User.Entity.User;
import com.example.bookrecordService.domain.User.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public SignUpResponseDto signUpUser(String username, String password, Long age){
        // 새롭게 알게된점 : 1. 엔티티 안에 필드를 다 호출할  필요가 없다.
        // 2. new User를 기능시키려면 User파일 내에서 불러오려는 파일의 생성자를 만든다.
        // 3. 엔티티는 request, response를 포함한 모든 필드가 있다. 그 중에 필요한 것만 호출하면 된다.

        User user = new User(username, password, age);
        User savedUser = userRepository.save(user);

        return new SignUpResponseDto(savedUser.getId(), savedUser.getUsername(), savedUser.getAge());
    }

    public UserResponseDto findUserById(Long id) {
        // 유저 단건조회하는 법(Get)
        User user = userRepository.findByIdOrElseThrow(id); // 1. 유저객체를 예외처리한다.
        return UserResponseDto.toDto(user);   // 2. 이후 유저 객체를 리턴화한다.
    }

    public void updateUser(Long id, String oldPassword, String newPassword) {
        // 유저 수정하는 법(Patch)
        User finduser = userRepository.findByIdOrElseThrow(id);  // 1. 유저객체를 예외처
        if(!finduser.getPassword().equals(oldPassword)){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"비밀번호가 일치하지 않습니다.");
        }
        finduser.updatePassword(newPassword);
    }

    public void deleteUser(Long id){
        // 유저 삭제하는 법(Delete)
        User user = userRepository.findByIdOrElseThrow(id); // 1. 유저 객체를 예외처리
        userRepository.delete(user);  // 2. delete 삭제 기능
    }

}
