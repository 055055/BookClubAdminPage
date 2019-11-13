package com.example.somoim.error;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(ServiceException.class)
    public ResponseEntity<?> serviceException(ServiceException e){
        return new ResponseEntity(e.getServiceError().getResultError(),e.getServiceError().getHttpStatus());

    }
}
