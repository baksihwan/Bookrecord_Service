package com.example.bookrecordService.Book.Controller;

import com.example.bookrecordService.Book.Service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
}
