package com.userJPA.C1220460.UserJpaApiC1220460.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandling {
    @ExceptionHandler(UsernotFoundExcetions.class)
    public ResponseEntity<String> handleUserNotFound(UsernotFoundExcetions ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ExistingEmailExceptions.class)
    public ResponseEntity<String> handleExistingEmail(ExistingEmailExceptions ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);


}
}
