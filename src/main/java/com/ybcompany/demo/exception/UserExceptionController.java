package com.ybcompany.demo.exception;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * Advice to catch Users Exceptions
 *
 * @see ConstraintViolationException
 */
@ControllerAdvice
public class UserExceptionController {
    /**
     * Handle ConstraintViolationException
     *
     * @param exception
     * @return Response with message
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity<Object> exception(ConstraintViolationException exception) {
        return new ResponseEntity<>(exception.getCause().getMessage(), HttpStatus.CONFLICT);
    }
}
