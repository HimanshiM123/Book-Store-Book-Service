package com.bridgelabz.bookservice.exception;

public class BookException extends RuntimeException {
    private int statusCode;
    private String statusMessage;

    public BookException(int statusCode, String statusMessage){
        super(statusMessage);
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
    }
}
