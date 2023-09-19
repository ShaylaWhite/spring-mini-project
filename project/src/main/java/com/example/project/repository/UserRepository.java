package com.example.project.repository;

import com.example.project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
//Purpose:It extends JpaRepository<User, Long>, which means it will provide basic CRUD (Create, Read, Update, Delete) operations for your User entity.
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // to register
    boolean existsByEmailAddress(String userEmailAddress);

    // to login, will find by email and if true will return a user obj
    //method will be used by the User Service // Service logic is there
    User findUserByEmailAddress(String emailAddress);
}
