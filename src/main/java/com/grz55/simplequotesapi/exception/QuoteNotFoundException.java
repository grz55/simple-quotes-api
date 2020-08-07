package com.grz55.simplequotesapi.exception;

public class QuoteNotFoundException extends RuntimeException {

    public QuoteNotFoundException(long id) {
        super("Could not find quote id = " + id);
    }
}
