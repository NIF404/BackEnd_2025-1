package com.example.bcsd;

import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class DBExceptionHandler {

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<String> handleEmailAlreadyExistsException(EmailAlreadyExistsException e){
        return ResponseEntity.status(e.getStatusCode()).body(e.getMessage());
    }

    @ExceptionHandler(EntityHasArticleException.class)
    public ResponseEntity<String> handleEntityHasArticleException(EntityHasArticleException e){
        return ResponseEntity.status(e.getStatusCode()).body(e.getMessage());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException e){
        return ResponseEntity.status(e.getStatusCode()).body(e.getMessage());
    }

    @ExceptionHandler(InvalidRequestBodyException.class)
    public ResponseEntity<String> handleInvalidRequestBodyException(InvalidRequestBodyException e){
        return ResponseEntity.status(e.getStatusCode()).body(e.getMessage());
    }

    @ExceptionHandler(ReferencedEntityNotFoundException.class)
    public ResponseEntity<String> handleReferencedEntityNotFoundException(ReferencedEntityNotFoundException e){
        return ResponseEntity.status(e.getStatusCode()).body(e.getMessage());
    }
}
