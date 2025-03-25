package com.example.bookrecordService.Friend.Repository;

import com.example.bookrecordService.Friend.Entity.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendRepository extends JpaRepository<Friend, Integer> {
}
