package com.example.proyectoFormativo.Interface;

import com.example.proyectoFormativo.Dto.Response.HistoriaMedicaResponseDto;

public interface IHistoriaMedicaService {

    /**
     * Obtiene el historial completo de una mascota por su ID.
     * Incluye datos de la mascota y toda su lista de consultas.
     */
    HistoriaMedicaResponseDto getHistoriaCompleta(Integer mascotaId);
}