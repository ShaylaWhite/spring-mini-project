package com.example.project.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// This annotation marks this class as an exception to be handled by Spring's exception handling mechanism.
@ResponseStatus(HttpStatus.NOT_FOUND)
public class InformationNotFoundException extends RuntimeException {

    // Constructor that accepts a message to be displayed when this exception is thrown.
    public InformationNotFoundException(String message) {
        // Call the constructor of the superclass (RuntimeException) with the provided message.
        super(message);
    }
}

//The InformationNotFoundException class is used to create a custom exception that represents a "not found" situation in your application. When thrown, it will result in an HTTP response with a status code of 404 (NOT_FOUND).
