package com.grz55.simplequotesapi.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class QuoteNotFoundAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(QuoteNotFoundException.class)
    public ResponseEntity<Object> quoteNotFoundHandler(QuoteNotFoundException ex, WebRequest webRequest) {
        String response = ex.getMessage();
        return handleExceptionInternal(ex, response, HttpHeaders.EMPTY, HttpStatus.NOT_FOUND, webRequest);
    }
}
