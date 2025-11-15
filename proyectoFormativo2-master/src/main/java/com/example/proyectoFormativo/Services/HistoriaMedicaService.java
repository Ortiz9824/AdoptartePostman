package com.example.proyectoFormativo.Services;

import com.example.proyectoFormativo.Dto.Response.ConsultaResponseDto;
import com.example.proyectoFormativo.Dto.Response.HistoriaMedicaResponseDto;
import com.example.proyectoFormativo.Interface.IHistoriaMedicaService;
import com.example.proyectoFormativo.Model.ConsultaMedica;
import com.example.proyectoFormativo.Model.HistoriaMedica;
import com.example.proyectoFormativo.Repository.IHistoriaMedicaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HistoriaMedicaService implements IHistoriaMedicaService {

    private final IHistoriaMedicaRepository historiaRepo;

    @Override
    public HistoriaMedicaResponseDto getHistoriaCompleta(Integer mascotaId) {
        // 1. Busca el historial usando el método que creamos
        HistoriaMedica historia = historiaRepo.findByMascota_Id(mascotaId)
                .orElseThrow(() -> new RuntimeException("No se encontró historial para la mascota con ID: " + mascotaId));

        // 2. Mapea la entidad a DTO
        return convertirAHistoriaDto(historia);
    }

    // --- MÉTODOS PRIVADOS DE MAPEO ---

    private HistoriaMedicaResponseDto convertirAHistoriaDto(HistoriaMedica historia) {
        HistoriaMedicaResponseDto dto = new HistoriaMedicaResponseDto();
        dto.setId(historia.getId());
        dto.setFechaCreacion(historia.getFechaCreacion());

        // Mapear datos de la mascota y dueño
        dto.setMascotaId(historia.getMascota().getId());
        dto.setMascotaNombre(historia.getMascota().getNombreMascota());
        dto.setMascotaRaza(historia.getMascota().getRazaMascota());
        dto.setDuenoNombre(historia.getMascota().getUsuario().getFirstname() + " " + historia.getMascota().getUsuario().getLastname());

        // REQUISITO: Mapear la lista de consultas
        // Si la lista está vacía, es la "primera vez"
        dto.setConsultas(
                historia.getConsultas().stream() // Obtiene la List<ConsultaMedica>
                        .map(this::convertirAConsultaDto) // Mapea cada una a DTO
                        .collect(Collectors.toList())
        );

        return dto;
    }

    private ConsultaResponseDto convertirAConsultaDto(ConsultaMedica consulta) {
        ConsultaResponseDto dto = new ConsultaResponseDto();
        dto.setId(consulta.getId());
        dto.setFechaConsulta(consulta.getFechaConsulta());
        dto.setSintomas(consulta.getSintomas());
        dto.setDiagnostico(consulta.getDiagnostico());
        dto.setTratamiento(consulta.getTratamiento());
        dto.setObservaciones(consulta.getObservaciones());

        // Mapear nombre del veterinario
        if (consulta.getVeterinario() != null) {
            dto.setVeterinarioNombre("Dr. " + consulta.getVeterinario().getFirstname());
        }

        return dto;
    }
}