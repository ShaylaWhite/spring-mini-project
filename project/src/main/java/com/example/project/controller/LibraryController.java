package com.example.project.controller;

import com.example.project.model.Author;
import com.example.project.model.Book;
import com.example.project.model.Category;
import com.example.project.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/library")
public class LibraryController {

    @Autowired
    private LibraryService libraryService;

    /**
     * Get a list of all books.
     *
     * @return List<Book> A list of all books.
     */
    @GetMapping(path = "/books") // GET http://localhost:9092/api/library/books
    public List<Book> getBooks() {
        return libraryService.getBooks();
    }

    /**
     * Get a book by its unique ID.
     *
     * @param bookId The ID of the book to retrieve.
     * @return ResponseEntity<Book> The book if found, or 404 Not Found if not found.
     */
    @GetMapping(path = "/books/{bookId}") // GET http://localhost:9092/api/library/books/{bookId}
    public ResponseEntity<Book> getBook(@PathVariable Long bookId) {
        Optional<Book> book = libraryService.getBook(bookId);
        if (book.isPresent()) {
            return ResponseEntity.ok(book.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Get a list of all authors.
     *
     * @return List<Author> A list of all authors.
     */
    @GetMapping(path = "/authors") // GET http://localhost:9092/api/library/authors
    public List<Author> getAuthors() {
        return libraryService.getAuthors();
    }

    /**
     * Get an author by their unique ID.
     *
     * @param authorId The ID of the author to retrieve.
     * @return ResponseEntity<Author> The author if found, or 404 Not Found if not found.
     */
    @GetMapping(path = "/authors/{authorId}") // GET http://localhost:9092/api/library/authors/{authorId}
    public ResponseEntity<Author> getAuthor(@PathVariable Long authorId) {
        Optional<Author> author = libraryService.getAuthor(authorId);
        if (author.isPresent()) {
            return ResponseEntity.ok(author.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Get a list of all categories.
     *
     * @return List<Category> A list of all categories.
     */
    @GetMapping(path = "/categories") // GET http://localhost:9092/api/library/categories
    public List<Category> getCategories() {
        return libraryService.getCategories();
    }
}