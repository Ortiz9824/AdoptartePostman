package com.example.proyectoFormativo.Services;

import com.example.proyectoFormativo.Dto.Response.ConsultaResponseDto;
import com.example.proyectoFormativo.Dto.Response.HistoriaMedicaResponseDto;
// import com.example.proyectoFormativo.Interface.IHistoriaMedicaService; // Interfaz de servicio si la usas
import com.example.proyectoFormativo.Model.ConsultaMedica;
import com.example.proyectoFormativo.Model.HistoriaMedica;
import com.example.proyectoFormativo.Repository.IHistoriaMedicaRepository; // Usamos el nombre de clase sin la 'I'
import com.example.proyectoFormativo.Repository.IMascotaRepository; // Necesario para crear un historial
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.transaction.annotation.Transactional; // Importante para métodos con múltiples repositorios

@Service
@RequiredArgsConstructor
// Si usas IHistoriaMedicaService, descomenta la línea de implements
// public class HistoriaMedicaService implements IHistoriaMedicaService {
public class HistoriaMedicaService {

    private final IHistoriaMedicaRepository historiaRepo;
    private final IMascotaRepository mascotaRepo;


    public Optional<HistoriaMedica> obtenerPorId(Long historialId) {
        return historiaRepo.findById(historialId);
    }


    @Transactional(readOnly = true)
    public HistoriaMedicaResponseDto getHistoriaCompleta(Long mascotaId) {


        HistoriaMedica historia = historiaRepo.findByMascota_Id(mascotaId)
                .orElseThrow(() -> new RuntimeException("No se encontró historial para la mascota con ID: " + mascotaId));

        return convertirAHistoriaDto(historia);
    }


    @Transactional
    public HistoriaMedica actualizarHistorial(Long id, HistoriaMedica historialDetalles) {
        return historiaRepo.findById(id)
                .map(historialExistente -> {

                    historialExistente.setDescripcion(historialDetalles.getDescripcion());
                    historialExistente.setRecomendaciones(historialDetalles.getRecomendaciones());


                    return historiaRepo.save(historialExistente);
                })
                .orElseThrow(() -> new RuntimeException("Historial Médico no encontrado con ID: " + id));
    }


    @Transactional
    public HistoriaMedica guardarHistorial(HistoriaMedica historial) {

        if (historial.getFechaEvaluacion() == null) {
            historial.setFechaEvaluacion(LocalDateTime.now());
        }
        return historiaRepo.save(historial);
    }



    private HistoriaMedicaResponseDto convertirAHistoriaDto(HistoriaMedica historia) {
        HistoriaMedicaResponseDto dto = new HistoriaMedicaResponseDto();
        dto.setId(historia.getId());

        dto.setFechaCreacion(historia.getFechaEvaluacion());

        dto.setDescripcion(historia.getDescripcion());
        dto.setRecomendaciones(historia.getRecomendaciones());


        dto.setMascotaId(historia.getMascota().getId());
        dto.setMascotaNombre(historia.getMascota().getNombreMascota());
        dto.setMascotaRaza(historia.getMascota().getRazaMascota());


        dto.setConsultas(
                historia.getConsultas().stream()
                        .map(this::convertirAConsultaDto)
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

        if (consulta.getVeterinario() != null) {
            dto.setVeterinarioNombre("Dr. " + consulta.getVeterinario().getNombre());
        }

        return dto;
    }
}