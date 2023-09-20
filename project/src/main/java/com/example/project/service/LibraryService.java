package com.example.project.service;

import com.example.project.exception.*;
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


// B O O K S

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
    // Update book details by its ID
    /**
     * Update book details by its ID.
     *
     * @param bookId       The ID of the book to update.
     * @param updatedBook  The updated book object with new values.
     * @return The updated book.
     * @throws BookNotFoundException if the specified book ID is not found.
     */
    public Book updateBook(Long bookId, Book updatedBook) {
        Optional<Book> optionalBook = bookRepository.findById(bookId);

        if (optionalBook.isPresent()) {
            Book bookToUpdate = optionalBook.get();
            // Update the fields of the existing book with the new values
            bookToUpdate.setTitle(updatedBook.getTitle());
            bookToUpdate.setAuthor(updatedBook.getAuthor());
            bookToUpdate.setCategory(updatedBook.getCategory());
            bookToUpdate.setIsbn(updatedBook.getIsbn());
            bookToUpdate.setAvailable(updatedBook.isAvailable());
            // Save the updated book to the repository
            return bookRepository.save(bookToUpdate);
        } else {
            throw new BookNotFoundException("Book not found"); // Book with the given ID not found
        }
    }


    // Delete a book by its ID
    /**
     * Delete a book by its ID.
     *
     * @param bookId The ID of the book to delete.
     * @return True if the book was successfully deleted, false otherwise.
     * @throws BookNotFoundException if the specified book ID is not found.
     */
    public boolean deleteBook(Long bookId) {
        Optional<Book> optionalBook = bookRepository.findById(bookId);

        if (optionalBook.isPresent()) {
            Book bookToDelete = optionalBook.get();
            bookRepository.delete(bookToDelete);
            return true; // Book was successfully deleted
        } else {
            throw new BookNotFoundException("Book not found"); // Book with the given ID not found
        }
    }




// AUTHORS

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
     * Update author details by their ID.
     *
     * @param authorId     The ID of the author to update.
     * @param updatedAuthor The updated author details.
     * @return The updated author entity.
     * @throws AuthorNotFoundException if the specified author ID is not found.
     */
    public Author updateAuthor(Long authorId, Author updatedAuthor) {
        Optional<Author> optionalAuthor = authorRepository.findById(authorId);

        if (optionalAuthor.isPresent()) {
            Author authorToUpdate = optionalAuthor.get();
            // Update the fields of the existing author with the new values
            authorToUpdate.setName(updatedAuthor.getName());

            // Save the updated author to the repository
            return authorRepository.save(authorToUpdate);
        } else {
            throw new AuthorNotFoundException("Author not found"); // Author with the given ID not found
        }
    }

    /**
     * Delete an author by their ID.
     *
     * @param authorId The ID of the author to delete.
     * @return True if the author was successfully deleted, false if the author was not found.
     */
    public boolean deleteAuthor(Long authorId) {
        Optional<Author> optionalAuthor = authorRepository.findById(authorId);

        if (optionalAuthor.isPresent()) {
            Author authorToDelete = optionalAuthor.get();
            authorRepository.delete(authorToDelete);
            return true; // Author was successfully deleted
        } else {
            return false; // Author with the given ID not found
        }
    }


// CATEGORY
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

    // Update an existing category's name by its ID
    /**
     * Update an existing category's name by its ID.
     *
     * @param categoryId The ID of the category to be updated.
     * @param newName    The new name for the category.
     * @return The updated category.
     * @throws InformationExistException   if the new name is the same as the existing name.
     * @throws InformationNotFoundException if the category with the specified ID is not found.
     */
    public Category updateCategoryName(Long categoryId, String newName) {
        // Find the category by its ID
        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);

        if (categoryOptional.isPresent()) {
            // Get the existing category
            Category existingCategory = categoryOptional.get();

            if (newName.equals(existingCategory.getName())) {
                // If the new name is the same as the existing name, throw an exception
                throw new InformationExistException("The category name is already " + newName);
            } else {
                // Update the category's name and save it
                existingCategory.setName(newName);
                return categoryRepository.save(existingCategory);
            }
        } else {
            // If the category with the specified ID is not found, throw an exception
            throw new InformationNotFoundException("Category with id " + categoryId + " not found.");
        }
    }

    // Delete a category by its ID
    /**
     * Delete a category by its ID.
     *
     * @param categoryId The ID of the category to be deleted.
     * @return An Optional containing the deleted category, if found.
     * @throws InformationNotFoundException if the category with the specified ID is not found.
     */
    public Optional<Category> deleteCategory(Long categoryId) {
        // Find the category by its ID
        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);

        if (categoryOptional.isPresent()) {
            // Delete the category by its ID
            categoryRepository.deleteById(categoryId);
            return categoryOptional;
        } else {
            // If the category with the specified ID is not found, throw an exception
            throw new InformationNotFoundException("Category with id " + categoryId + " not found.");
        }
    }

}
