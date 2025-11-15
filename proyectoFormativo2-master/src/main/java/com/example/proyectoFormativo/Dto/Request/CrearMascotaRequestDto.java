package com.example.proyectoFormativo.Dto.Request;

import lombok.Data;
// 👇 ¡CAMBIO AQUÍ! De 'javax' a 'jakarta'
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Data // Lombok crea getters, setters, etc.
public class CrearMascotaRequestDto {

    @NotBlank
    private String nombre;

    @NotBlank
    private String raza;

    @NotNull
    private Integer tamanoMascotaId; // ID del tamaño

    @NotNull
    private Integer tipoViviendaId;  // ID del tipo de vivienda

    // Añade aquí cualquier otro campo que necesites al crear
}