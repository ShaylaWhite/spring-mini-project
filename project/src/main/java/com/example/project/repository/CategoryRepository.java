package com.example.project.repository;

import com.example.project.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//declare a repository interface and extend the JpaRepository interface, you are essentially creating a repository class with built-in database access method
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(String categoryName);

    List<Category> findByUserId(Long userId);

    Category findByIdAndUserId(Long categoryId, Long userId);
}
