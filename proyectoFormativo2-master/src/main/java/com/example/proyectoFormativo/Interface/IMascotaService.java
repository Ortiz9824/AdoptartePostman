package com.example.proyectoFormativo.Interface;

// Imports de los DTOs
import com.example.proyectoFormativo.Dto.Request.ActualizarMascotaRequestDto;
import com.example.proyectoFormativo.Dto.Request.CrearMascotaRequestDto;
import com.example.proyectoFormativo.Dto.Response.MascotaResponseDto;

import java.util.List;
import java.util.Optional;

// <-- AQUÍ SÍ VA LA INTERFAZ
public interface IMascotaService {

    MascotaResponseDto crearMascotaYHistoria(CrearMascotaRequestDto mascotaDto, String username);

    List<MascotaResponseDto> obtenerMascotasActivas(String username);

    Optional<MascotaResponseDto> getMascotaById(Integer id, String username);

    MascotaResponseDto updateMascota(Integer id, ActualizarMascotaRequestDto mascotaDto, String username);

    void borrarMascotaLogico(Integer id, String username);
}