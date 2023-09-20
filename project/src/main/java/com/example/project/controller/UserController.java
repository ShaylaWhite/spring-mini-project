package com.example.project.controller;

import com.example.project.model.User;
import com.example.project.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * UserController is a Spring REST controller that handles user-related operations.
 */
@RestController // This annotation specifies that this class is a REST controller.
@RequestMapping(path = "/auth/users") // This sets the base path for all endpoints in this controller.
public class UserController {

    private UserService userService; // Declare a private field to hold the UserService instance.

    /**
     * Constructs a new UserController instance.
     *
     * @param userService The UserService instance to be injected as a dependency.
     */
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Create User - POST endpoint
     *
     * @param userObject The User object received in the request body.
     * @return The created User object.
     */
    @PostMapping("/register") // http://localhost:9092/auth/users/register/
    public User createUser(@RequestBody User userObject) {
        return userService.createUser(userObject);
    }
}

