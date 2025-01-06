package io.fam.famapi.exception;

public class LengthException extends RuntimeException {
    
    public LengthException(final String value, final int min, final int max) {
        super(value + " length must be between " + min + " and " + max + " characters");
    }
    
}
