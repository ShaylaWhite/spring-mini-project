package com.example.project.model;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(nullable = false)
    private String isbn;

    @Column(nullable = false)
    private boolean available;

    // Add a relationship mapping to represent the "Book belongs to a User (Borrower)" relationship.
    @ManyToOne
    @JoinColumn(name = "borrower_id")
    private User borrower;

    // Constructors, getters, setters, and other methods.
}
