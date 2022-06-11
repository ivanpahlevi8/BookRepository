package com.example.thymeleafdemo.domain;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class BookList {
    @NotNull
    private String id;
    @NotBlank
    @NotNull
    private String reader;
    private String isbn;
    private String title;
    private String author;
    private String description;

    public BookList()
    {
        this.id = UUID.randomUUID().toString();
    }
    public BookList(String reader, String isbn, String title, String author, String desc)
    {
        this.reader = reader;
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.description = desc;
    }
}
