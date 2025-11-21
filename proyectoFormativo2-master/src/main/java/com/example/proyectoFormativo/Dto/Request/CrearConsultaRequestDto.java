package com.example.proyectoFormativo.Dto.Request;

import lombok.Data;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;

@Data
public class CrearConsultaRequestDto {

    @NotNull


    private Long historiaMedicaId;

    @NotBlank
    private String diagnostico;

    @NotBlank
    private String tratamiento;

    private String sintomas;
    private String observaciones;
}