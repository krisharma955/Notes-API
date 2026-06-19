package com.K955.Notes.API.Exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionAPI> handleResourceNotFoundException(ResourceNotFoundException exception) {
        ExceptionAPI exceptionAPI = new ExceptionAPI(HttpStatus.NOT_FOUND,
                exception.getResourceName() +" with id "+ exception.getResourceId() +" not found",
                Instant.now());
        log.error(exceptionAPI.message());
        return ResponseEntity.status(exceptionAPI.status()).body(exceptionAPI);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ExceptionAPI> handleBadRequest(BadRequestException exception) {
        ExceptionAPI exceptionAPI = new ExceptionAPI(HttpStatus.BAD_REQUEST,
                exception.getMessage(), Instant.now());
        log.error(exceptionAPI.message());
        return ResponseEntity.status(exceptionAPI.status()).body(exceptionAPI);
    }

}
