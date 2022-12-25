package com.example.lr_7.exception;

public class CollectionException extends Exception {
    public CollectionException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "CollectionException {"
                + "сообщение = " + getMessage()
                + "} ";
    }
}
