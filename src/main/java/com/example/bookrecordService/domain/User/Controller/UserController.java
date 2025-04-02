package com.example.bookrecordService.domain.User.Controller;

import com.example.bookrecordService.domain.User.Dto.UserRequestDto;
import com.example.bookrecordService.domain.User.Dto.UserResponseDto;
import com.example.bookrecordService.domain.User.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> findAllUser(){
        List<UserResponseDto> userResponseDto = userService.findAllUser();
        return ResponseEntity.ok(userResponseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findByIdUser(@PathVariable Long id){
        UserResponseDto userResponseDto = userService.findByIdUser(id);
        return ResponseEntity.ok(userResponseDto);
    }

    @PostMapping
    public ResponseEntity<UserResponseDto> saveUser(@RequestBody UserRequestDto requestDto){
        UserResponseDto userResponseDto = userService.saveUser(requestDto);
        return ResponseEntity.ok(userResponseDto);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable Long id){
        UserResponseDto userResponseDto = userService.updateUser(id);
        return ResponseEntity.ok(userResponseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
