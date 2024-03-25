package com.persons.manager.config;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.persons.manager.dto.Response;
import com.persons.manager.util.Constants;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Response> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        Response response = new Response(Constants.WRONG_FORMAT, Constants.WRONG_FORMAT_MSG);
        return ResponseEntity.badRequest().body(response);
    }
    
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<Response> handleMethodNotAllowed(HttpRequestMethodNotSupportedException ex) {
    	Response respose = new Response(Constants.NOT_ALLOWED, Constants.NOT_ALLOWED_MSG);
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(respose);
    }

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<Response> runtimeExceptionHandler(RuntimeException exception){
    	Response error = new Response(Constants.INTERNAL_ERROR_SERVER,exception.getMessage());
    	return new ResponseEntity<>(error, HttpStatus.ACCEPTED);
    }
    
    @ExceptionHandler(MissingPathVariableException.class)
    public ResponseEntity<Response> handleMissingPathVariable(MissingPathVariableException ex) {
    	ex.getMessage();
    	Response respose = new Response(Constants.WRONG_DEFINITION, Constants.WRONG_DEFINITION_MSG);
    	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respose);
    }
    
}