package com.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.naming.AuthenticationException;

@RestControllerAdvice
public class AllExceptionHandler {

    //기능
    //ex) plannerId에 해당하는 플래너가 없을 때."
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException eIllegalArgument) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(eIllegalArgument.getMessage());
    }

    //ex) 댓글이 10개 제한인데 추가하려고 할 때."
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<String> handleIllegalState(IllegalStateException eIllegalState) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(eIllegalState.getMessage());
    }

    //ex) @Valid로 받는 Request의 @NotBlank, @Size의 기준에 부적합할 때."
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleMethodArgumentNotValid(MethodArgumentNotValidException eMethodArgumentNotValid) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(eMethodArgumentNotValid.getMessage());
    }

    //ex) 비밀번호가 일치하지 않을 때."
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<String> handleAuthentication(AuthenticationException eAuthentication) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(eAuthentication.getMessage());
    }
}
