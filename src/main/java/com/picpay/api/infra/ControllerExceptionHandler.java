package com.picpay.api.infra;

import com.picpay.api.dtos.ExceptionDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity threatDuplicateDocumentException(DataIntegrityViolationException ex) {
        return ResponseEntity.badRequest().body(new ExceptionDTO("Document already exists", "400"));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity threatNotFoundException(EntityNotFoundException ex) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity threatGeneralException(Exception ex) {
        return ResponseEntity.internalServerError().body(new ExceptionDTO(ex.getMessage(), "500"));
    }
}
