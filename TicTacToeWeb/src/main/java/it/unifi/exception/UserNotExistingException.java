package it.unifi.exception;

public class UserNotExistingException extends Exception {
    public UserNotExistingException() {
    }

    public UserNotExistingException(String message) {
        super(message);
    }
}
