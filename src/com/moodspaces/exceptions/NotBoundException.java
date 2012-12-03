package com.moodspaces.exceptions;

public class NotBoundException extends Exception {

    private static final long serialVersionUID = 8378577995760700281L;

    @Override
    public String getMessage() {
        return "The requested service is not bound!";
    }
}
