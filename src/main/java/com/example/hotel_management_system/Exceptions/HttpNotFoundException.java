package com.example.hotel_management_system.Exceptions;

public class HttpNotFoundException extends RuntimeException{
    public HttpNotFoundException(String message) {
        super(message);
    }
}
