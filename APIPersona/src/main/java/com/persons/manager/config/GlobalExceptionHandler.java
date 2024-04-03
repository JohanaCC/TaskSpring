package com.persons.manager.config;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.messaging.handler.annotation.support.MethodArgumentNotValidException;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.persons.manager.dto.Response;
import com.persons.manager.util.Constants;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
	@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach((error) -> {
            String fieldName = error.getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        Response response = new Response(Constants.WRONG_FORMAT,  errors.toString());
        return ResponseEntity.badRequest().body(response);
    }
	
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