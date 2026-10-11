package com.springboot.placement.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	 @ExceptionHandler(ResourceNotFoundException.class)
	 public ResponseEntity<Map<String, Object>> handleResourceNotFound(ResourceNotFoundException ex) {
	        Map<String, Object> response = new HashMap<>();

	        response.put("timestamp", LocalDateTime.now());
	        response.put("status", HttpStatus.NOT_FOUND.value());
	        response.put("error", "Not Found");
	        response.put("message", ex.getMessage());

	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	    
	 }

	 @ExceptionHandler(MethodArgumentNotValidException.class)
	    @ResponseStatus(HttpStatus.BAD_REQUEST)
	    public Map<String, String> handleFieldValidations(
	            MethodArgumentNotValidException ex) {

	        Map<String, String> errors = new HashMap<>();

	        ex.getBindingResult().getFieldErrors()
	            .forEach(error -> {
	                errors.put(
	                    error.getField(),
	                    error.getDefaultMessage()
	                );
	            });

	        return errors;
	    }
	    	 
	 @ExceptionHandler(BusinessException.class)
	 @ResponseStatus(HttpStatus.BAD_REQUEST)
	 public Map<String, String> handleBusinessException(BusinessException ex) {

	     Map<String, String> error = new HashMap<>();
	     error.put("error", "Business Error");
	     error.put("message", ex.getMessage());
	     return error;
	 
	 }
	 
	 
	 @ExceptionHandler(Exception.class)
	 public ResponseEntity<Map<String, Object>> handleGeneralException(Exception ex) {
	        Map<String, Object> response = new HashMap<>();

	        response.put("timestamp", LocalDateTime.now());
	        response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
	        response.put("error", "Internal Server Error");
	        response.put("message", ex.getMessage());

	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	    }
	 	 
	}
