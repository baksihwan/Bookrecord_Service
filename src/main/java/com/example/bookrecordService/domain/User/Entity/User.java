package com.example.bookrecordService.domain.User.Entity;

import com.example.bookrecordService.global.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Getter
@Table(name = "user")
public class User extends BaseEntity {

    }

