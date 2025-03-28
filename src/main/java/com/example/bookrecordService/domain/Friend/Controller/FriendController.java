package com.example.bookrecordService.domain.Friend.Controller;

import com.example.bookrecordService.domain.Friend.Service.FriendService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("friends")
public class FriendController {

    private final FriendService friendService;
}
