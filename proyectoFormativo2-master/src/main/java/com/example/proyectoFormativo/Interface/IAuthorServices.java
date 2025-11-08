package com.example.proyectoFormativo.Interface;

import com.example.proyectoFormativo.Dto.AuthorDto;
import com.example.proyectoFormativo.Dto.Response.AuthResponse;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public interface IAuthorServices {
    AuthResponse<List<AuthorDto>> findAll();
    AuthResponse<AuthorDto> findById(Long id);
    AuthResponse<AuthorDto> create(AuthorDto dto);
    AuthResponse<AuthorDto> update(Long id, AuthorDto author);
    void delete(Long id);
}
