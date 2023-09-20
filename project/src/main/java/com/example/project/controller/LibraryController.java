package com.example.project.controller;

import com.example.project.exception.AuthorNotFoundException;
import com.example.project.exception.CategoryNotFoundException;
import com.example.project.exception.InformationExistException;
import com.example.project.model.Author;
import com.example.project.model.Book;
import com.example.project.model.Category;
import com.example.project.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.project.exception.InformationNotFoundException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/library")
public class LibraryController {

    @Autowired
    private LibraryService libraryService;

    @GetMapping(path = "/books") // GET http://localhost:9092/api/library/books
    public List<Book> getBooks() {
        return libraryService.getBooks();
    }

    // Get a book by its unique ID.
    @GetMapping(path = "/books/{bookId}") // GET http://localhost:9092/api/library/books/{bookId}
    public ResponseEntity<Book> getBook(@PathVariable Long bookId) {
        Optional<Book> book = libraryService.getBook(bookId);
        return book.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PostMapping("/authors/{authorId}/books")
    public ResponseEntity<?> createBook(@PathVariable Long authorId,
                                        @RequestParam Long categoryId,
                                        @RequestParam String title,
                                        @RequestParam String isbn) {
        try {
            // Call the bookService to create the book with the retrieved author
            libraryService.createBook(authorId, categoryId, title, isbn);
            return ResponseEntity.ok("Book created successfully");
        } catch (AuthorNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Author not found");
        } catch (CategoryNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found");
        } catch (InformationExistException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Book with the same title already exists");
        } catch (InformationNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Title cannot be empty");
        }
    }

    // Get a list of all authors.
    @GetMapping(path = "/authors") // GET http://localhost:9092/api/library/authors
    public List<Author> getAuthors() {
        return libraryService.getAuthors();
    }

    // Get an author by their unique ID.
    @GetMapping(path = "/authors/{authorId}") // GET http://localhost:9092/api/library/authors/{authorId}
    public ResponseEntity<Author> getAuthor(@PathVariable Long authorId) {
        Optional<Author> author = libraryService.getAuthor(authorId);
        return author.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Create a new author.
    @PostMapping(path = "/authors") // POST http://localhost:9092/api/library/authors
    public ResponseEntity<Author> createAuthor(@RequestParam String authorName) {
        Author createdAuthor = libraryService.createAuthor(authorName);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAuthor);
    }

    // Get a list of all categories.
    @GetMapping(path = "/categories") // GET http://localhost:9092/api/library/categories
    public List<Category> getCategories() {
        return libraryService.getCategories();
    }

    // Get a Category by its unique ID.
    @GetMapping(path = "/categories/{categoryId}") // GET http://localhost:9092/api/library/categories/{categoryId}
    public ResponseEntity<Category> getCategoryById(@PathVariable Long categoryId) {
        Optional<Category> category = libraryService.getCategoryById(categoryId);
        return category.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Create a new category.
    @PostMapping(path = "/categories") // POST http://localhost:9092/api/library/categories
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        try {
            Category createdCategory = libraryService.createCategory(category);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdCategory);
        } catch (InformationExistException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
