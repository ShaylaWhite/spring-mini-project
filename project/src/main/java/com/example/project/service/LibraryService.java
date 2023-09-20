package com.example.project.service;

import com.example.project.exception.AuthorNotFoundException;
import com.example.project.exception.InformationExistException;
import com.example.project.exception.InformationNotFoundException;
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

    @Autowired
    public LibraryService(
            CategoryRepository categoryRepository,
            BookRepository bookRepository,
            AuthorRepository authorRepository) {
        this.categoryRepository = categoryRepository;
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    /**
     * Retrieve a list of all books.
     *
     * @return List<Book> A list of all books.
     */
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    /**
     * Retrieve a book by its unique ID.
     *
     * @param bookId The ID of the book to retrieve.
     * @return An Optional containing the book if found, or empty if not found.
     */
    public Optional<Book> getBook(Long bookId) {
        return bookRepository.findById(bookId);
    }

    /**
     * Create a new book.
     *
     * @param book The book object representing the book to be created.
     * @return The newly created book.
     * @throws InformationExistException if a book with the same title already exists.
     */
    public Book createBook(Book book) {
        if (book.getTitle() == null || book.getTitle().isEmpty()) {
            throw new InformationNotFoundException("Title cannot be empty.");
        }
        if (book.getAuthorName() == null || book.getAuthorName().isEmpty()) {
            throw new InformationNotFoundException("Author name cannot be empty.");
        }

        Book existingBook = bookRepository.findByTitle(book.getTitle());

        if (existingBook != null) {
            throw new InformationExistException("Book with the same title already exists.");
        } else {
            Author author = authorRepository.findByName(book.getAuthorName());

            if (author == null) {
                // Handle author not found based on your application's logic (e.g., throw an exception or create a new author).
                throw new AuthorNotFoundException("Author not found for name: " + book.getAuthorName());
            }

            book.setAuthor(author); // Set the author before saving the book

            return bookRepository.save(book);
        }
    }


    /**
     * Retrieve a list of all authors.
     *
     * @return List<Author> A list of all authors.
     */
    public List<Author> getAuthors() {
        return authorRepository.findAll();
    }

    /**
     * Retrieve an author by their unique ID.
     *
     * @param authorId The ID of the author to retrieve.
     * @return An Optional containing the author if found, or empty if not found.
     */
    public Optional<Author> getAuthor(Long authorId) {
        return authorRepository.findById(authorId);
    }

    /**
     * Retrieve a list of all categories.
     *
     * @return List<Category> A list of all categories.
     */
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    /**
     * Retrieve a category by its unique ID.
     *
     * @param categoryId The ID of the category to retrieve.
     * @return An Optional containing the category if found, or empty if not found.
     */
    public Optional<Category> getCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId);
    }

    /**
     * Create a new category.
     *
     * @param category The category object representing the category to be created.
     * @return The newly created category.
     * @throws InformationExistException if a category with the same name already exists.
     */
    public Category createCategory(Category category) {
        if (category.getName() == null || category.getName().isEmpty()) {
            throw new IllegalArgumentException("Category name cannot be empty.");
        }

        Category existingCategory = categoryRepository.findByName(category.getName());

        if (existingCategory != null) {
            throw new InformationExistException("Category with the same name already exists.");
        } else {
            return categoryRepository.save(category);
        }
    }
}
