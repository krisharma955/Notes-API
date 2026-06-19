package com.K955.Notes.API.Exceptions;

import org.springframework.http.HttpStatus;

import java.time.Instant;

public record ExceptionAPI(
        HttpStatus status,
        String message,
        Instant timestamp
) {
}
