package com.example.project.controller;

import com.example.project.model.User;
import com.example.project.reponse.LoginResponse;
import com.example.project.request.LoginRequest;
import com.example.project.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

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
    /**
     * Login User - POST endpoint
     * Maps POST requests to the "/login/" path.
     * Handles user login via HTTP POST request.
     *
     * @param loginRequest The LoginRequest object received in the request body, containing user login information.
     * @return A ResponseEntity representing the entire HTTP response, including status code, headers, and response body.
     *         The response body is of type LoginResponse, which typically contains a JWT token upon successful login.
     *         This method maps POST requests to the "/login/" path and is accessible from outside the class.
     */
    @PostMapping(path = "/login/") // http://localhost:9092/auth/users/login/
    public ResponseEntity<LoginResponse> loginUser(@RequestBody LoginRequest loginRequest) {

        Optional<String> jwtToken = userService.loginUser(loginRequest);
        //Step 1 -  attempt user login and retrieve a JWT token.
        //This code tries to log a user in using the information provided in loginRequest. If the login is successful, it stores a JWT token in the jwtToken variable.
        // If the login fails, the variable remains empty. The Optional is used to handle both scenarios.
        if (jwtToken.isPresent()) {
            // Step 2 - If a JWT token is present, the login was successful.
            // Create a ResponseEntity with a LoginResponse containing the JWT token.
            return ResponseEntity.ok(new LoginResponse(jwtToken.get()));
        } else {
            // Step 3 - If no JWT token is present, the login failed.
            // Return a ResponseEntity with an UNAUTHORIZED status and a LoginResponse indicating authentication failure.
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new LoginResponse("Authentication failed"));
        }

    }
}


