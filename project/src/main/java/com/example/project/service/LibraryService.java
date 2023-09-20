package com.example.project.service;

import com.example.project.exception.AuthorNotFoundException;
import com.example.project.exception.CategoryNotFoundException;
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
     * Find an author by their unique ID.
     *
     * @param authorId The ID of the author to retrieve.
     * @return The author if found, or null if not found.
     */
    public Author findById(Long authorId) {
        return authorRepository.findById(authorId).orElse(null);
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
     * Create a new book associated with an author.
     *
     * @param authorId  The ID of the author to associate the book with.
     * @param categoryId The ID of the category to associate the book with.
     * @param title     The title of the book.
     * @param isbn      The ISBN of the book.
     * @return The newly created book.
     * @throws InformationExistException if a book with the same title already exists.
     * @throws AuthorNotFoundException   if the specified author ID is not found.
     * @throws CategoryNotFoundException if the specified category ID is not found.
     */
    public Book createBook(Long authorId, Long categoryId, String title, String isbn) {
        if (title == null || title.isEmpty()) {
            throw new InformationNotFoundException("Title cannot be empty.");
        }

        // Check if a book with the same title already exists
        Book existingBook = bookRepository.findByTitle(title);

        if (existingBook != null) {
            throw new InformationExistException("Book with the same title already exists.");
        } else {
            Author author = authorRepository.findById(authorId)
                    .orElseThrow(() -> new AuthorNotFoundException("Author not found for ID: " + authorId));

            Category category = categoryRepository.findById(categoryId)
                    .orElseThrow(() -> new CategoryNotFoundException("Category not found for ID: " + categoryId));

            // Create a new Book object and set its properties
            Book book = new Book();
            book.setTitle(title);
            book.setAuthor(author);
            book.setCategory(category);
            book.setIsbn(isbn);
            book.setAvailable(true);
            book.setBorrower(null);

            return bookRepository.save(book); // Add this return statement
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
     * Create a new author.
     *
     * @param authorName The name of the author to be created.
     * @return The newly created author.
     */
    public Author createAuthor(String authorName) {
        // Check if the author with the given name already exists
        Author existingAuthor = authorRepository.findByName(authorName);

        if (existingAuthor != null) {
            // Author with the same name already exists, return the existing author
            return existingAuthor;
        } else {
            // Author with the given name doesn't exist, create a new author using the constructor
            Author newAuthor = new Author(authorName); // Use the constructor that accepts authorName

            // Save the new author entity to the database
            return authorRepository.save(newAuthor);
        }
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
