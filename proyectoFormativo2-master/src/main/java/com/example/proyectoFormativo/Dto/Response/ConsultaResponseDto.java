package com.example.proyectoFormativo.Dto.Response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ConsultaResponseDto {
    private Integer id;
    private LocalDateTime fechaConsulta;
    private String sintomas;
    private String diagnostico;
    private String tratamiento;
    private String observaciones;
    private String veterinarioNombre; // Para saber qué doctor la hizo
}