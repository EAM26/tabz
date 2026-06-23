package com.emcode.tabz.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleRecordNotFoundException(RecordNotFoundException ex,
                                                                       HttpServletRequest req) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                req.getRequestURI()));
    }

    @ExceptionHandler(TabAlreadyClaimedException.class)
    public ResponseEntity<ErrorResponse>handleTabAlreadyClaimedException(TabAlreadyClaimedException ex,
                                                                         HttpServletRequest req) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse(HttpStatus.CONFLICT.value(),
                ex.getMessage(),
                req.getRequestURI()));
    }
}
