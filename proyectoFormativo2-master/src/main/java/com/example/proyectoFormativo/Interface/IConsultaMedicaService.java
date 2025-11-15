package com.example.proyectoFormativo.Interface;

import com.example.proyectoFormativo.Dto.Request.CrearConsultaRequestDto;
import com.example.proyectoFormativo.Dto.Response.ConsultaResponseDto;

public interface IConsultaMedicaService {

    /**
     * Crea un nuevo registro de consulta médica.
     * 'vetUsername' se obtiene del token de seguridad.
     */
    ConsultaResponseDto crearConsulta(CrearConsultaRequestDto dto, String vetUsername);
}