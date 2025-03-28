package com.example.bookrecordService.domain.Friend.Repository;

import com.example.bookrecordService.domain.Comment.Entity.Comment;
import com.example.bookrecordService.domain.Friend.Entity.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendRepository extends JpaRepository<Friend, Integer> {

    default Friend findByIdOrElseThrow(Long id){
        return findById(id).orElseThrow(()-> new NotFoundByIdException(ExceptionType.FRIEND_NOT_FOUND));
}
