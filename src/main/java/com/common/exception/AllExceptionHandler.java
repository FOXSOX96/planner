package com.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.naming.AuthenticationException;

@RestControllerAdvice
public class AllExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException eIllegalArgument) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(eIllegalArgument.getMessage());
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<String> handleIllegalState(IllegalStateException eIllegalState) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(eIllegalState.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleMethodArgumentNotValid(MethodArgumentNotValidException eMethodArgumentNotValid) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(eMethodArgumentNotValid.getMessage());
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<String> handleAuthentication(AuthenticationException eAuthentication) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(eAuthentication.getMessage());
    }
}
