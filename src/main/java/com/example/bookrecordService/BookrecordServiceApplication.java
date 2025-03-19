package com.example.bookrecordService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan(basePackages = "com.example.bookrecordService")
@SpringBootApplication
public class BookrecordServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookrecordServiceApplication.class, args);
        System.out.println("Hello World");
    }

}
