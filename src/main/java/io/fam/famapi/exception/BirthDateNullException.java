package io.fam.famapi.exception;

import java.util.UUID;

public class BirthDateNullException extends RuntimeException {

    public BirthDateNullException(UUID id) {
        super("Birth date for person with ID " + id + " is null.");
    }
}
