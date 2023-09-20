package com.example.project.service;

import com.example.project.model.Author;
import com.example.project.model.Book;
import com.example.project.model.Category;
import com.example.project.repository.AuthorRepository;
import com.example.project.repository.BookRepository;
import com.example.project.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibraryService {
    private final CategoryRepository categoryRepository;
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
//Constructor injection allows you to provide instances of these repositories when creating a LibraryService object,
    @Autowired
    public LibraryService(
            CategoryRepository categoryRepository,
            BookRepository bookRepository,
            AuthorRepository authorRepository) {
        this.categoryRepository = categoryRepository;
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    // BOOK OPERATIONS

    // Retrieve a list of all books
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    // Retrieve a book by ID
    public Optional<Book> getBook(Long bookId) {
        return bookRepository.findById(bookId);
    }

    // AUTHOR OPERATIONS

    // Retrieve a list of all authors
    public List<Author> getAuthors() {
        return authorRepository.findAll();
    }

    // Retrieve an author by ID
    public Optional<Author> getAuthor(Long authorId) {
        return authorRepository.findById(authorId);
    }

    // CATEGORY OPERATIONS

    // Retrieve a list of all categories
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }
}
