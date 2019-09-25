package com.onlineretail.app;

public class DuplicateUsernameException extends Exception{
    public DuplicateUsernameException(String message) {
        super(message);
    }
}
