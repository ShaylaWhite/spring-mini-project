package com.example.project.controller;

import com.example.project.model.User;
import com.example.project.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



//A RestController in Spring is a specialized version of a @Controller that is used to define RESTful web services. It's a common annotation used in Spring MVC applications to indicate that a particular class serves the role of a controller in a RESTful architectur
@RestController // This annotation specifies that this class is a REST controller.
@RequestMapping(path = "/auth/users") // This sets the base path for all endpoints in this controller.
public class UserController {

    private UserService userService; // Declare a private field to hold the UserService instance.

                                        // Constructor to inject the UserService dependency.
    public UserController(UserService userService) {
        this.userService = userService;
    }

    //CREATE USER(POST =)
    @PostMapping("/register") // http://localhost:9092/auth/users/register/
    public User createUser(@RequestBody User userObject) {
        return userService.createUser(userObject);
    }
}
