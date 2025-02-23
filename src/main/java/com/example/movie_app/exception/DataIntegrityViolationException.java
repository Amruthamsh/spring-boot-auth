package com.example.movie_app.exception;

import org.springframework.dao.NonTransientDataAccessException;

/**
 * Exception thrown when a database integrity constraint is violated.
 */
public class DataIntegrityViolationException extends NonTransientDataAccessException {

    public DataIntegrityViolationException(String msg) {
        super(msg);
    }

    public DataIntegrityViolationException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
