package com.example.bookrecordService.domain.Comment.Entity;

import com.example.bookrecordService.domain.Friend.Entity.Friend;
import com.example.bookrecordService.domain.User.Entity.User;
import com.example.bookrecordService.global.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.Id;

@Getter
@Entity
@Table(name = "comment")
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    private Friend friend;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    }
}

