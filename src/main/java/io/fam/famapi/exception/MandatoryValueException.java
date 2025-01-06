package io.fam.famapi.exception;

public class MandatoryValueException extends RuntimeException {
    
    public MandatoryValueException(final String value) {
        super(value + " is a mandatory value and cannot be null or empty.");
    }
    
}
