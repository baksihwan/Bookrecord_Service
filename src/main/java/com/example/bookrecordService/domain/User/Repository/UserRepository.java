package com.example.bookrecordService.domain.User.Repository;

import com.example.bookrecordService.domain.User.Entity.User;
import com.example.bookrecordService.global.exception.ExceptionType;
import com.example.bookrecordService.global.exception.NotFoundByIdException;
import jakarta.annotation.PostConstruct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    //메모리에 저장할 사용자 리스트
    final List<User> userList = new ArrayList<>();

    // ID로 사용자 찾기
    public default Optional<User> findById(String id) {
        return userList.stream()
                .filter(it -> it.getId().equals(id))
                .findFirst();
    }

    // 이메일로 사용자 찾기
    public default Optional<User> findByEmail(String email) {
        return userList.stream()
                .filter(it -> it.getEmail().equals(email))
                .findFirst();
    }

    // 애플리케이션 시작 시 사용자 데이터를 초기화하는 메소드
    @PostConstruct
    public default void init() {
        // 초기 사용자 데이터 추가
        userList.add(User.builder()
                .id(UUID.randomUUID().toString()) // 랜덤으로 생성된 유니크 ID
                .email("shs00925@naver.com")
                .password("1234")
                .build());

        userList.add(User.builder()
                .id(UUID.randomUUID().toString())
                .email("test1@naver.com")
                .password("1234")
                .build());

        userList.add(User.builder()
                .id(UUID.randomUUID().toString())
                .email("user1234@naver.com")
                .password("1234")
                .build());
    }

    default User findByIdOrElseThrow(Long id){
        return findById(id).orElseThrow(()->new NotFoundByIdException(ExceptionType.USER_NOT_FOUND));
    }
}
