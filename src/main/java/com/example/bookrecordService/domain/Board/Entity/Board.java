package com.example.bookrecordService.domain.Board.Entity;

import com.example.bookrecordService.domain.User.Entity.User;
import com.example.bookrecordService.global.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name = "board")
public class Board extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    private String title;

    @Column(length = 1000)
    private String contents;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public Board(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    public Board() {}

    public void setUser(User user){
        this.user = user;
    }
}



