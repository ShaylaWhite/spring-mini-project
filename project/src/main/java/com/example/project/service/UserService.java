package com.example.project.service;

import com.example.project.model.User;
import com.example.project.repository.UserRepository;

public class UserService {
    private final UserRepository userRepository;
    //declares a private, final field userRepository of type UserRepository in the UserService class, which will hold the injected UserRepository bean, and once initialized, its value cannot be changed

    public User findUserByEmailAddress(String emailAddress) {
        return userRepository.findUserByEmailAddress(emailAddress);
    }
}
