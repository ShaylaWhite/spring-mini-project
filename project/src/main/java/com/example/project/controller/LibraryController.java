package com.example.project.controller;

import com.example.project.exception.*;
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



    // BOOK CRUD OPERATIONS
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
    // Update a book by its ID
    @PutMapping("/books/{bookId}") // PUT http://localhost:9092/api/library/books/{bookId}
    public ResponseEntity<?> updateBook(@PathVariable Long bookId, @RequestBody Book updatedBook) {
        try {
            libraryService.updateBook(bookId, updatedBook);
            return ResponseEntity.ok("Book updated successfully");
        } catch (BookNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found");
        }
    }

    // Delete a book by its ID
    @DeleteMapping("/books/{bookId}") // DELETE http://localhost:9092/api/library/books/{bookId}
    public ResponseEntity<?> deleteBook(@PathVariable Long bookId) {
        try {
            libraryService.deleteBook(bookId);
            return ResponseEntity.ok("Book deleted successfully");
        } catch (BookNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found");
        }
    }

    @PostMapping("/authors/{authorId}/books") //POST http://localhost:9092/api/library/authors/{authorId}/book
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


    // AUTHOR CRUD OPERATIONS
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

  // Update Author
  @PutMapping("/authors/{authorId}") // PUT http://localhost:9092/api/library/authors/{authorId}
  public ResponseEntity<?> updateAuthor(@PathVariable Long authorId, @RequestBody Author updatedAuthor) {
      try {
          libraryService.updateAuthor(authorId, updatedAuthor);
          return ResponseEntity.ok("Author updated successfully");
      } catch (AuthorNotFoundException e) {
          return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Author not found");
      }
  }






    // CATEGORIES CRUD OPERATIONS
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

    // Update an existing category by ID
    @PutMapping(path = "/categories/{categoryId}") // PUT http://localhost:9092/api/categories/{categoryId}
    public Category updateCategory(@PathVariable(value = "categoryId") Long categoryId, @RequestBody Category category) {
        return libraryService.updateCategoryName(categoryId, String.valueOf(category));
    }

    // Delete a category by ID
    @DeleteMapping(path = "/categories/{categoryId}") // DELETE http://localhost:9092/api/categories/{categoryId}
    public Optional<Category> deleteCategory(@PathVariable(value = "categoryId") Long categoryId) {
        return libraryService.deleteCategory(categoryId);
    }
}
