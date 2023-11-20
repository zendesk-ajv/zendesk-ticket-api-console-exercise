package com.zendesk.marcie.exception;

public class ApiUnavailableException extends RuntimeException {

    public ApiUnavailableException(String message) {
        super(message);
    }
    
}
