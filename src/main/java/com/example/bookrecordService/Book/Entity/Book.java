package com.example.bookrecordService.Book.Entity;

import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Entity
@NoArgsConstructor
public class Book {
    @Id
}
