package com.example.proyectoFormativo.Interface;

import com.example.proyectoFormativo.Dto.Response.HistoriaMedicaResponseDto;
import java.util.Optional;

public interface IHistoriaMedicaService {

    // AÑADE ESTE MÉTODO:
    HistoriaMedicaResponseDto getHistoriaCompleta(Integer mascotaId);

    // (Aquí van los otros métodos que tenías, como getHistoriaMedicaById, etc.)
}