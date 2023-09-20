package com.example.project.service;

import com.example.project.exception.InformationExistException;
import com.example.project.model.User;
import com.example.project.repository.UserRepository;
import com.example.project.request.LoginRequest;
import com.example.project.security.JWTUtils;
import com.example.project.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * The UserService class provides methods for user-related operations such as registration and retrieval.
 */
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final JWTUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    // Constructor injection: Dependencies are injected through the constructor.
    // UserRepository represents the repository for user-related data access.
    // PasswordEncoder is used for securely hashing user passwords.

    /**
     * Constructs a new UserService instance.
     *
     * @param userRepository         The repository for user-related data access.
     * @param passwordEncoder        The encoder used to securely hash user passwords.
     * @param jwtUtils               The utility class for JSON Web Token (JWT) operations.
     * @param authenticationManager  The authentication manager for user login.
     */
    public UserService(UserRepository userRepository, @Lazy PasswordEncoder passwordEncoder,
                       JWTUtils jwtUtils,
                       @Lazy AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
        this.authenticationManager = authenticationManager;
    }

    // Find User By Email Address Method

    /**
     * Find a user by their email address.
     *
     * @param emailAddress The email address of the user to retrieve.
     * @return The User object if found, or null if not found.
     */
    public User findUserByEmailAddress(String emailAddress) {
        // Use the userRepository to find a user by their email address.
        return userRepository.findUserByEmailAddress(emailAddress);
    }

    // Create User Method

    /**
     * Create a new user with the provided user object.
     *
     * @param userObject The User object representing the user to be created.
     * @return The created User object.
     * @throws InformationExistException If a user with the same email address already exists.
     */
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
    /**
     * Attempt to log in a user with the provided login credentials.
     *
     * @param loginRequest The LoginRequest object containing user login credentials.
     * @return An Optional containing a JWT token if authentication is successful, or an empty Optional if authentication fails.
     */
    public Optional<String> loginUser(LoginRequest loginRequest) {
        // Create an authentication token with the user's email address and password.
        UsernamePasswordAuthenticationToken authenticationToken = new
                UsernamePasswordAuthenticationToken(loginRequest.getEmailAddress(), loginRequest.getPassword());

        try {
            // Attempt to authenticate the user.
            Authentication authentication = authenticationManager.authenticate(authenticationToken);

            // If authentication is successful, set the user's authentication in the security context.
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Get the user details from the authenticated principal.
            MyUserDetails myUserDetails = (MyUserDetails) authentication.getPrincipal();

            // Generate a JWT token for the authenticated user.
            return Optional.of(jwtUtils.generateJwtToken(myUserDetails));
        } catch (Exception e) {
            // If authentication fails, return an empty optional.
            return Optional.empty();
        }
    }
}

