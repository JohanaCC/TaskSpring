package com.manager.config;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.manager.dto.Response;
import com.manager.util.Constants;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Response> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        Response response = new Response(Constants.WRONG_FORMAT, Constants.WRONG_FORMAT_MSG+" "+ex.getMessage() );
        return ResponseEntity.badRequest().body(response);
    }
    
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<Response> handleMethodNotAllowed(HttpRequestMethodNotSupportedException ex) {
    	Response respose = new Response(Constants.NOT_ALLOWED, Constants.NOT_ALLOWED_MSG);
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(respose);
    }

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<Response> runtimeExceptionHandler(RuntimeException ex){
    	Response error = new Response(Constants.INTERNAL_ERROR_SERVER,ex.getMessage());
    	return new ResponseEntity<>(error, HttpStatus.ACCEPTED);
    }
    
}