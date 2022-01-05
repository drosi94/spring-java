package com.project.smdb.controller.advice;

import com.project.smdb.controller.advice.model.Error;
import com.project.smdb.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
class ResourceControllerAdvice {

    @ExceptionHandler(ResourceNotFoundException.class)
    ResponseEntity<?> resourceNotFoundHandler(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Error(ex.getMessage()));
    }
}