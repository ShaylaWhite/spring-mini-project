package com.example.project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(nullable = false)
    private String isbn;

    @Column(nullable = false)
    private boolean available;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "borrower_id")
    private User borrower;


    // Constructors, getters, setters, and other methods.

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public User getBorrower() {
        return borrower;
    }

    public void setBorrower(User borrower) {
        this.borrower = borrower;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author=" + author +
                ", category=" + category +
                ", isbn='" + isbn + '\'' +
                ", available=" + available +
                ", borrower=" + borrower +
                '}';
    }

    public String getAuthorName() {
        if (author != null) {
            return author.getName(); // 'getName()' method in the Author class
        } else {
            return null; // Return null if there's no associated author
        }
    }
}
