package com.zahariaca.nflowtest.controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book")
public class BookController {

    @GetMapping("/{id}")
    public Book findById(@PathVariable long id) {
        return Book.builder()
                .name("name-" + id)
                .build();
    }

    @GetMapping("/")
    public List<Book> findBooks() {
        return List.of(Book.builder()
                .name("name-findBoks")
                .build());
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Book updateBook(
            @PathVariable("id") final String id, @RequestBody final Book book) {
        return Book.builder()
                .name("name-update-" + id)
                .build();
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    static class Book {
        private String name;
    }
}