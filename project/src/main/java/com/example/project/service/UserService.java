package com.example.project.service;

import com.example.project.exception.InformationExistException;
import com.example.project.model.User;
import com.example.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final JWTUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    // Constructor injection: Dependencies are injected through the constructor.
    // UserRepository represents the repository for user-related data access.
    // PasswordEncoder is used for securely hashing user passwords.
    @Autowired
    public UserService(UserRepository userRepository, @Lazy PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
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
//    // Login in User Method
//    public Optional<String> loginUser(LoginRequest loginRequest) {
//        UsernamePasswordAuthenticationToken authenticationToken = new
//                UsernamePasswordAuthenticationToken(loginRequest.getEmailAddress(), loginRequest.getPassword());
//        try {
//            Authentication authentication = authenticationManager.authenticate(authenticationToken);
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//            MyUserDetails myUserDetails = (MyUserDetails) authentication.getPrincipal();
//            return Optional.of(jwtUtils.generateJwtToken(myUserDetails));
//        } catch (Exception e) {
//            return Optional.empty();
//        }
//    }
}
