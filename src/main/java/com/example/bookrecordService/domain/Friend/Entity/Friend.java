package com.example.bookrecordService.domain.Friend.Entity;

import jakarta.persistence.Entity;
import lombok.Getter;

@Entity
@Getter
@Table(name = "friend")
public class Friend extends BaseEntity{
}
