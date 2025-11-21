package com.example.proyectoFormativo.Dto.Response;

import lombok.Data;
import java.time.LocalDate;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class HistoriaMedicaResponseDto {

    private Long id;
    private LocalDate fechaCreacion;

    private String descripcion;
    private String recomendaciones;


    // Datos de la mascota (simplificados)
    private Integer mascotaId;
    private String mascotaNombre;
    private String mascotaRaza;
    private String duenoNombre;


    private List<ConsultaResponseDto> consultas;

    public void setFechaCreacion(LocalDateTime fechaEvaluacion) {
    }
}