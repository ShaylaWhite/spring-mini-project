package com.example.project.controller;

import com.example.project.exception.InformationExistException;
import com.example.project.model.Author;
import com.example.project.model.Book;
import com.example.project.model.Category;
import com.example.project.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    /**
     * Get a Category by its unique ID.
     *
     * @param categoryId The ID of the category to retrieve.
     * @return ResponseEntity<Category> The category if found, or 404 Not Found if not found.
     */
    @GetMapping("/categories/{categoryId}") // GET http://localhost:9092/api/library/categories/{categoryId}
    public ResponseEntity<Category> getCategoryById(@PathVariable Long categoryId) {
        Optional<Category> category = libraryService.getCategoryById(categoryId); // Use categoryService
        if (category.isPresent()) {
            return ResponseEntity.ok(category.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    /**
     * Create a new category.
     *
     * @param category The category object representing the category to be created.
     * @return ResponseEntity<Category> The newly created category, or a conflict response if a category with the same name already exists.
     */
    @PostMapping("/categories")
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        try {
            Category createdCategory = libraryService.createCategory(category);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdCategory);
        } catch (InformationExistException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

}