package com.example.project.controller;

import com.example.project.model.Author;
import com.example.project.model.Book;
import com.example.project.model.Category;
import com.example.project.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/library")
public class LibraryController {

    @Autowired
    private LibraryService libraryService;

    //Books
// Get all books
    @GetMapping(path = "/books") // GET http://localhost:9092/api/library/books
    public List<Book> getBooks() {
        return libraryService.getBooks();
    }

    //Authors
// Get all authors
    @GetMapping(path = "/authors") // GET http://localhost:9092/api/library/authors
    public List<Author> getAuthors() {
        return libraryService.getAuthors();
    }

    // Categories

    // Get all categories

    @GetMapping(path = "/categories") // GET http://localhost:9092/api/library/categories
    public List<Category> getCategories() {
        return libraryService.getCategories();
    }
}
