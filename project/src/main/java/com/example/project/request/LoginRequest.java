package com.example.project.request;

/**
 * LoginRequest is a Data Transfer Object (DTO) used for capturing user login information.
 * It contains fields for the user's email address and password.
 */
public class LoginRequest {

    /**
     * The user's email address for login.
     */
    private String emailAddress;

    /**
     * The user's password for login.
     */
    private String password;

    // Getters and setters for emailAddress and password

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
