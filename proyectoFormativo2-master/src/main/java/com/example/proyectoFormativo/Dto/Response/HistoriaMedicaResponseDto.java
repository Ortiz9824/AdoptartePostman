package com.example.proyectoFormativo.Dto.Response;

import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class HistoriaMedicaResponseDto {

    private Integer id;
    private LocalDate fechaCreacion;

    // Datos de la mascota (simplificados)
    private Integer mascotaId;
    private String mascotaNombre;
    private String mascotaRaza;
    private String duenoNombre;

    // ¡REQUISITO! La lista de todos sus registros
    private List<ConsultaResponseDto> consultas;
}