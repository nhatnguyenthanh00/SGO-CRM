package com.example.sgo_crm.exception;

public class InvalidFormatException extends RuntimeException{

    public InvalidFormatException(String message) {
        super(message);
    }

    public InvalidFormatException(String message, Throwable cause) {
        super(message, cause);
    }
}
