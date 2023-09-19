package com.example.project.service;

import com.example.project.model.User;
import com.example.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service                            //@Service is used to mark a class as a service layer component.
public class UserService {
    private final UserRepository userRepository;
    // Declares a private, final field 'userRepository' of type 'UserRepository' in the UserService class.
    // This field will hold the injected UserRepository bean, and once initialized, its value cannot be changed.

    private final PasswordEncoder passwordEncoder;

    @Autowired

    public UserService(UserRepository userRepository, @Lazy PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Constructor that accepts a 'UserRepository' bean as a parameter and initializes the 'userRepository' field.


    //Methods

    // Find User By Email Address Method
    public User findUserByEmailAddress(String emailAddress) {
        return userRepository.findUserByEmailAddress(emailAddress);
    }

    // Create User Method

    public User createUser(User userobject){
        // defines a method named createUser within a class, and it expects a User object (userObject) as a parameter
        if (!userRepository.existsByEmailAddress(userObject.getEmailAddress())) {
            userObject.setPassword(passwordEncoder.encode(userObject.getPassword()));
            return userRepository.save(userObject);
        } else {
            throw new InformationExistException("user with email address " + userObject.getEmailAddress() + " already exists");
        }
    }



}

