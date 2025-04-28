package com.Hospital_Management_System.exception; // Package declaration for exceptions

public class ResourceNotFoundException extends RuntimeException { // Custom exception class that extends RuntimeException
    public ResourceNotFoundException(String message) { // Constructor that accepts a message
        super(message); // Passes the message to the superclass (RuntimeException) for proper handling
    }
}
