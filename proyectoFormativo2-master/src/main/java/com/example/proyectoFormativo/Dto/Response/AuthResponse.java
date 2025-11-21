package com.example.proyectoFormativo.Dto.Response;

import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Builder; // <-- 1. AÑADE (o verifica) ESTE IMPORT
import lombok.Data;

@Data
@Builder
@AllArgsConstructor

public class AuthResponse<T> {

    private String token;
    // --- Getters & Setters ---
    private int httpStatusCode;
    private String message;
    private T data;
    private List<String> errors;
    private Object extraData;

    // Paginación
    private Integer totalRecords;
    private Integer pageNumber;
    private Integer pageSize;

    public Integer getTotalPages() {
        if (pageSize != null && pageSize > 0 && totalRecords != null)
            return (int) Math.ceil((double) totalRecords / pageSize);
        return null;
    }

    // --- Constructores ---
    public AuthResponse() {}

    public AuthResponse(int httpStatusCode, String message, T data) {
        this.httpStatusCode = httpStatusCode;
        this.message = message;
        this.data = data;
    }

    public void setHttpStatusCode(int httpStatusCode) { this.httpStatusCode = httpStatusCode; }

    public void setMessage(String message) { this.message = message; }

    public void setData(T data) { this.data = data; }

    public void setErrors(List<String> errors) { this.errors = errors; }

    public void setExtraData(Object extraData) { this.extraData = extraData; }

    public void setTotalRecords(Integer totalRecords) { this.totalRecords = totalRecords; }

    public void setPageNumber(Integer pageNumber) { this.pageNumber = pageNumber; }

    public void setPageSize(Integer pageSize) { this.pageSize = pageSize; }
}