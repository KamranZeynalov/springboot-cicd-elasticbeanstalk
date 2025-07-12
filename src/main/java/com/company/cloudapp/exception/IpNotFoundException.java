package com.company.cloudapp.exception;

public class IpNotFoundException extends RuntimeException {

    public IpNotFoundException(String message) {
        super(message);
    }
}
