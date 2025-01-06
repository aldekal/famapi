package io.fam.famapi.exception;

public class ValueAlreadyExistsException extends RuntimeException {
    
    public ValueAlreadyExistsException(final String value) {
        super(value + " already exists.");
    }
    
}
