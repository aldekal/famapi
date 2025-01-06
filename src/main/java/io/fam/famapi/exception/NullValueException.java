package io.fam.famapi.exception;

public class NullValueException extends RuntimeException {
    
    public NullValueException(final String value) {
        super(value + " cannot be null.");
    }

}
