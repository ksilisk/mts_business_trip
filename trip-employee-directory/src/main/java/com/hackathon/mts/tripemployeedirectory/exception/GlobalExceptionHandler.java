package com.hackathon.mts.tripemployeedirectory.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public ExceptionDetails handleEntityNotFound(Exception ex, WebRequest webRequest) {
        log.info("Handled resource not found exception. Description {}", webRequest.getDescription(false), ex);
        return ExceptionDetails.builder()
                .details(webRequest.getDescription(false))
                .message(ex.getMessage())
                .timestamp(System.currentTimeMillis())
                .statusCode(NOT_FOUND.value())
                .build();
    }
}
