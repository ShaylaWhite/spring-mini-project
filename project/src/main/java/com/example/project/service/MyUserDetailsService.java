package com.example.project.service;

import com.example.project.model.User;
import com.example.project.service.UserService;
import com.example.project.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * Load user-specific data by their email address during authentication.
     *
     * @param emailAddress The email address of the user to load.
     * @return UserDetails containing user details and authorities.
     * @throws UsernameNotFoundException If the user is not found.
     */
    @Override
    public UserDetails loadUserByUsername(String emailAddress) throws UsernameNotFoundException {
        // Load a user by their email address using the UserService.
        User user = userService.findUserByEmailAddress(emailAddress);

        // If the user is not found, throw a UsernameNotFoundException.
        if (user == null) {
            throw new UsernameNotFoundException("User not found with email address: " + emailAddress);
        }

        // Create and return a UserDetails object (MyUserDetails) based on the retrieved user.
        return new MyUserDetails(user);
    }
}