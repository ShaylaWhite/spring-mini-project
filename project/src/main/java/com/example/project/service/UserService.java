package com.example.project.service;

import com.example.project.exception.InformationExistException;
import com.example.project.model.User;
import com.example.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // Constructor injection: Dependencies are injected through the constructor.
    // UserRepository represents the repository for user-related data access.
    // PasswordEncoder is used for securely hashing user passwords.
    @Autowired
    public UserService(UserRepository userRepository, @Lazy PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Find User By Email Address Method
    public User findUserByEmailAddress(String emailAddress) {
        // Use the userRepository to find a user by their email address.
        return userRepository.findUserByEmailAddress(emailAddress);
    }

    // Create User Method
    public User createUser(User userObject) {
        // Check if a user with the same email address already exists.
        if (!userRepository.existsByEmailAddress(userObject.getEmailAddress())) {
            // Securely hash the user's password before storing it.
            userObject.setPassword(passwordEncoder.encode(userObject.getPassword()));
            // Save the new user to the database.
            return userRepository.save(userObject);
        } else {
            // If a user with the same email address exists, throw an exception.
            throw new InformationExistException("User with email address " + userObject.getEmailAddress() + " already exists");
        }
    }
}
