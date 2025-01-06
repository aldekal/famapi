package io.fam.famapi.exception;

public class InvalidValueException extends RuntimeException {
    
    public InvalidValueException(final String value) {
        super(value + " is invalid");
    }
    
}
