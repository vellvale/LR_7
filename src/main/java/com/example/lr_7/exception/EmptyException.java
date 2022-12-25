package com.example.lr_7.exception;

public class EmptyException extends Exception {
    public EmptyException() {
        super();
    }

    public EmptyException(String errorMessage) {
        super(errorMessage);
    }
}
