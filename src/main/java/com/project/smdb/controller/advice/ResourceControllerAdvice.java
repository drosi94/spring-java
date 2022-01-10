package com.project.smdb.controller.advice;

import com.project.smdb.DataLoader;
import com.project.smdb.exception.ResourceNotFoundException;
import com.project.smdb.exception.ServerErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
class ResourceControllerAdvice {

    private static final Logger logger = LoggerFactory.getLogger(ResourceControllerAdvice.class);

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    Map<String, String> resourceNotFoundHandler(ResourceNotFoundException ex) {
        logger.error(ex.getMessage());

        Map<String, String> errors = new HashMap<>();
        errors.put("error", ex.getMessage());

        return errors;
    }

    @ExceptionHandler(FileNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    Map<String, String> fileNotFoundHandler(FileNotFoundException ex) {
        logger.error(ex.getMessage());

        Map<String, String> errors = new HashMap<>();
        errors.put("error", "File requested does not exist");

        return errors;
    }


    @ExceptionHandler(ServerErrorException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    Map<String, String> serverErrorHandler(ServerErrorException ex) {
        logger.error(ex.getMessage());

        Map<String, String> errors = new HashMap<>();
        errors.put("error", ex.getMessage());

        return errors;
    }



    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        logger.error(ex.getMessage());

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}