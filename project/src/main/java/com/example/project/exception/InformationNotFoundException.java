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
