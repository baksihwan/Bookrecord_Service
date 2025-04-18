package com.example.bookrecordService.domain.User.Repository;

import com.example.bookrecordService.domain.User.Entity.User;
import com.example.bookrecordService.global.exception.ExceptionType;
import com.example.bookrecordService.global.exception.NotFoundByIdException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


@Component
@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    default User findByIdOrElseThrow(Long id){
        return findById(id).orElseThrow(()->new NotFoundByIdException(ExceptionType.USER_NOT_FOUND));
    }
}
