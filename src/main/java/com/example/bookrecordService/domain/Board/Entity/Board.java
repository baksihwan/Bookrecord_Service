package com.example.bookrecordService.domain.Board.Entity;

import com.example.bookrecordService.domain.User.Entity.User;
import com.example.bookrecordService.global.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.Id;

@Entity
@Getter
@Table(name = "board")
public class Board extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
}
