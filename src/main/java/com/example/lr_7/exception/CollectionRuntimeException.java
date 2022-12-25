package com.example.lr_7.exception;

public class CollectionRuntimeException extends RuntimeException {
    public CollectionRuntimeException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "CollectionRuntimeException {"
                + "сообщение = " + getMessage()
                + "} ";
    }
}
