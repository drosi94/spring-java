package com.project.smdb.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(Long id) {
        super("Could not find resource " + id);
    }
}
