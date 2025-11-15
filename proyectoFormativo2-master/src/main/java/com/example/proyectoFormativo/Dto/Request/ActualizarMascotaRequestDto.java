package com.example.proyectoFormativo.Dto.Request;

import lombok.Data;
// 👇 ¡CAMBIO AQUÍ! De 'javax' a 'jakarta'
import jakarta.validation.constraints.NotBlank;

@Data
public class ActualizarMascotaRequestDto {

    @NotBlank
    private String nombre;

    @NotBlank
    private String raza;

    // Puedes añadir más campos que permitas actualizar
    // (ej. tamanoMascotaId, tipoViviendaId)
}