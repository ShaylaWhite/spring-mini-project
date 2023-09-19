package com.example.project.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "profiles")
public class UserProfile {

//annotations are used to define the attributes (fields) of a Java class that represents an entity in a database
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private Date dateOfBirth;

    @Column(nullable = false)
    private String location;

    //This annotation defines a one-to-one relationship between this entity (UserProfile) and another entity (User). It indicates that each UserProfile is associated with one User.
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")          // the foreign key column in the database table that links the UserProfile entity to the User entity
    private User user;

    // Getters and setters are Java methods used to access and modify the private fields (attributes) of a class. They are part of the JavaBean convention, which is a naming convention for methods used to manipulate the properties of a Java class.
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}