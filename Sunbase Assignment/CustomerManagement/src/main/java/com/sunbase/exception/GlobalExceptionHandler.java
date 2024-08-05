package com.sunbase.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;


@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> exceptionHandler(Exception ex, WebRequest w) {
        ErrorDetails err = new ErrorDetails();
        err.setMessage(ex.getMessage());
        err.setDesc(w.getDescription(false));
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorDetails> noHandlerFoundExceptionHandler(NoHandlerFoundException ex, WebRequest w) {
        ErrorDetails err = new ErrorDetails();
        err.setMessage(ex.getMessage());
        err.setDesc(w.getDescription(false));
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDetails> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex) {
        ErrorDetails err = new ErrorDetails();
        err.setMessage("Validation Error");
        err.setDesc(ex.getBindingResult().getFieldError().getDefaultMessage());
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomerException.class)
    public ResponseEntity<ErrorDetails> customerExceptionHandler(CustomerException ex, WebRequest w) {
        ErrorDetails err = new ErrorDetails();
        err.setMessage(ex.getMessage());
        err.setDesc(w.getDescription(false));
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DuplicateDataException.class)
    public ResponseEntity<ErrorDetails> duplicateDataExceptionHandler(DuplicateDataException ex, WebRequest w) {
        ErrorDetails err = new ErrorDetails();
        err.setMessage(ex.getMessage());
        err.setDesc(w.getDescription(false));
        return new ResponseEntity<>(err, HttpStatus.CONFLICT); // Use CONFLICT status for duplicate data
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ErrorDetails> invalidCredentialsExceptionHandler(InvalidCredentialsException ex, WebRequest w) {
        ErrorDetails err = new ErrorDetails();
        err.setMessage(ex.getMessage());
        err.setDesc(w.getDescription(false));
        return new ResponseEntity<>(err, HttpStatus.UNAUTHORIZED); // Use UNAUTHORIZED status for invalid credentials
    }

    @ExceptionHandler(InvalidDataException.class)
    public ResponseEntity<ErrorDetails> invalidDataExceptionHandler(InvalidDataException ex, WebRequest w) {
        ErrorDetails err = new ErrorDetails();
        err.setMessage(ex.getMessage());
        err.setDesc(w.getDescription(false));
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoRecordFoundException.class)
    public ResponseEntity<ErrorDetails> noRecordFoundExceptionHandler(NoRecordFoundException ex, WebRequest w) {
        ErrorDetails err = new ErrorDetails();
        err.setMessage(ex.getMessage());
        err.setDesc(w.getDescription(false));
        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND); // Use NOT_FOUND status for no record found
    }
}
