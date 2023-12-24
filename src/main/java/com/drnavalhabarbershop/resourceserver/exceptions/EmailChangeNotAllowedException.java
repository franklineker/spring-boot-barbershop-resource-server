package com.drnavalhabarbershop.resourceserver.exceptions;

public class EmailChangeNotAllowedException extends Exception {
    public EmailChangeNotAllowedException() {
        super("Changing email is not allowed.");
    }

    public EmailChangeNotAllowedException(String message) {
        super(message);
    }
}
