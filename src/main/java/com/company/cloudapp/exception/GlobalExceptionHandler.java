package com.company.cloudapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ProjectNotFoundException.class, IpNotFoundException.class, IntegrationNotFoundException.class,
    ContractNotFoundException.class})
    public ResponseEntity<ErrorResponse> handleNotFoundExceptions(RuntimeException ex) {

        ErrorResponse model  =  ErrorResponse.builder()
                                             .errorCode(404)
                                             .message(ex.getMessage())
                                             .build();
        return new ResponseEntity<>(model, HttpStatus.NOT_FOUND);
    }
}
