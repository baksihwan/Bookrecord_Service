package com.example.bookrecordService.domain.Friend.Service;

import com.example.bookrecordService.domain.Friend.Repository.FriendRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FriendService {

    private final FriendRepository friendRepository;
}
