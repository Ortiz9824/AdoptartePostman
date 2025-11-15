package com.example.proyectoFormativo.Services;

import com.example.proyectoFormativo.Dto.Request.CrearConsultaRequestDto;
import com.example.proyectoFormativo.Dto.Response.ConsultaResponseDto;
import com.example.proyectoFormativo.Interface.IConsultaMedicaService;
import com.example.proyectoFormativo.Model.ConsultaMedica;
import com.example.proyectoFormativo.Model.HistoriaMedica;
import com.example.proyectoFormativo.Model.User;
import com.example.proyectoFormativo.Repository.IConsultaMedicaRepository;
import com.example.proyectoFormativo.Repository.IHistoriaMedicaRepository;
import com.example.proyectoFormativo.Repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ConsultaMedicaService implements IConsultaMedicaService {

    private final IConsultaMedicaRepository consultaRepo;
    private final IHistoriaMedicaRepository historiaRepo;
    private final IUserRepository userRepo;

    @Override
    @Transactional
    public ConsultaResponseDto crearConsulta(CrearConsultaRequestDto dto, String vetUsername) {

        // 1. Buscar al Veterinario (User)
        User veterinario = userRepo.findByUsername(vetUsername)
                .orElseThrow(() -> new RuntimeException("Veterinario no encontrado"));

        // 2. Buscar el Historial
        HistoriaMedica historia = historiaRepo.findById(dto.getHistoriaMedicaId())
                .orElseThrow(() -> new RuntimeException("Historial médico no encontrado"));

        // 3. Crear la nueva entidad ConsultaMedica
        ConsultaMedica nuevaConsulta = new ConsultaMedica();
        nuevaConsulta.setFechaConsulta(LocalDateTime.now());
        nuevaConsulta.setSintomas(dto.getSintomas());
        nuevaConsulta.setDiagnostico(dto.getDiagnostico());
        nuevaConsulta.setTratamiento(dto.getTratamiento());
        nuevaConsulta.setObservaciones(dto.getObservaciones());

        // 4. Vincular las relaciones
        nuevaConsulta.setHistoriaMedica(historia);
        nuevaConsulta.setVeterinario(veterinario);

        // 5. Guardar
        ConsultaMedica consultaGuardada = consultaRepo.save(nuevaConsulta);

        // 6. Devolver DTO (Hay que mapearlo)
        return convertirAConsultaDto(consultaGuardada);
    }

    // Método privado de mapeo
    private ConsultaResponseDto convertirAConsultaDto(ConsultaMedica consulta) {
        ConsultaResponseDto dto = new ConsultaResponseDto();
        dto.setId(consulta.getId());
        dto.setFechaConsulta(consulta.getFechaConsulta());
        dto.setSintomas(consulta.getSintomas());
        dto.setDiagnostico(consulta.getDiagnostico());
        dto.setTratamiento(consulta.getTratamiento());
        dto.setObservaciones(consulta.getObservaciones());

        if (consulta.getVeterinario() != null) {
            dto.setVeterinarioNombre("Dr. " + consulta.getVeterinario().getFirstname());
        }

        return dto;
    }
}