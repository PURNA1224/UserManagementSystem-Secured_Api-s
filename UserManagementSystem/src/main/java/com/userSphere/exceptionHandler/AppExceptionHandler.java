package com.userSphere.exceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppExceptionHandler {

    /**
     * Handle validation errors from @Valid annotated request bodies.
     * Returns a map of field names and corresponding error messages.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException exception) {
        Map<String, String> errorMap = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(error ->
            errorMap.put(error.getField(), error.getDefaultMessage())
        );
        return new ResponseEntity<>(errorMap, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle conflict when email already exists.
     * Returns a custom error message with timestamp.
     */
    @ExceptionHandler(MailAlreadyExistException.class)
    public ResponseEntity<Object> handleMailAlreadyExistException(MailAlreadyExistException exception) {
        ErrorMessage errorMessage = new ErrorMessage(new Date(), exception.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorMessage);
    }

    /**
     * Handle conflict when username already exists.
     */
    @ExceptionHandler(UserNameAlreadyExistException.class)
    public ResponseEntity<Object> handleUserNameAlreadyExistException(UserNameAlreadyExistException exception) {
        ErrorMessage errorMessage = new ErrorMessage(new Date(), exception.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorMessage);
    }

    /**
     * Handle user not found scenario.
     */
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException exception) {
        ErrorMessage errorMessage = new ErrorMessage(new Date(), exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }
}
