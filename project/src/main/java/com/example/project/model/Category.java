package com.example.project.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    // Add a relationship mapping to represent the "Category can have many Books" relationship.
    @OneToMany(mappedBy = "category")
    private List<Book> books;

    // Constructors, getters, setters, and other methods.

    public Category() {
        // Default constructor
    }

    public Category(String name) {
        this.name = name;
    }

    // Getters and setters for id and name

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
