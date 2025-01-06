package io.fam.famapi.exception;

public class ValuesNotMatchException extends RuntimeException {
    
    public ValuesNotMatchException(final String value1, final String value2) {
        super(value1 + " and " + value2 + " do not match");
    }
    
}
