package com.example.proyectoFormativo.Dto.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CrearConsultaRequestDto {

    @NotNull
    private Integer historiaMedicaId; // A qué historial pertenece

    @NotBlank
    private String sintomas;

    @NotBlank
    private String diagnostico;

    private String tratamiento;
    private String observaciones;

    // El ID del veterinario lo tomaremos del TOKEN de seguridad
}