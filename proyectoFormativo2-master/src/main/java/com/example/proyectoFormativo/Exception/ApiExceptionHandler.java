package com.example.proyectoFormativo.Exception;


import com.example.proyectoFormativo.Dto.Response.AuthResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public AuthResponse<Object> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(e -> e.getField() + ": " + e.getDefaultMessage())
                .toList();

        AuthResponse<Object> response = new AuthResponse<>();
        response.setHttpStatusCode(HttpStatus.BAD_REQUEST.value());
        response.setMessage("Errores de validación");
        response.setErrors(errors);
        return response;
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public AuthResponse<Object> handleConstraintViolation(ConstraintViolationException ex) {
        List<String> errors = ex.getConstraintViolations().stream()
                .map(v -> v.getPropertyPath() + ": " + v.getMessage())
                .toList();

        AuthResponse<Object> response = new AuthResponse<>();
        response.setHttpStatusCode(HttpStatus.BAD_REQUEST.value());
        response.setMessage("Validación fallida");
        response.setErrors(errors);
        return response;
    }
}