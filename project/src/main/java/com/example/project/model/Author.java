package com.example.project.model;

@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    // Add a relationship mapping to represent the "Author can have many Books" relationship.
    @OneToMany(mappedBy = "author")
    private List<Book> books;
