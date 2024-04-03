package com.example.sgo_crm.exception;

public class DataSaveException extends RuntimeException{
    public DataSaveException(String message) {
        super(message);
    }

    public DataSaveException(String message, Throwable cause) {
        super(message, cause);
    }
}
