package com.example.proyectoFormativo.Services;

import com.example.proyectoFormativo.Dto.Response.ConsultaResponseDto; // Ajusta si usas DTOs
import com.example.proyectoFormativo.Interface.ConsultaMedicaService; // <--- IMPORTANTE
import com.example.proyectoFormativo.Model.ConsultaMedica;
import com.example.proyectoFormativo.Repository.IConsultaMedicaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ConsultaMedicaService extends ConsultaMedicaService { // <--- ¡AGREGA ESTO!

    private final IConsultaMedicaRepository consultaMedicaRepository;

    @Override
    public List<ConsultaMedica> obtenerTodas() {
        return consultaMedicaRepository.findAll();
    }

    @Override
    public Optional<ConsultaMedica> obtenerPorId(Long id) {
        return consultaMedicaRepository.findById(id);
    }

    @Override
    @Transactional
    public ConsultaMedica guardarConsulta(ConsultaMedica consultaMedica) {
        return consultaMedicaRepository.save(consultaMedica);
    }

    @Override
    public void eliminarConsulta(Long id) {
        consultaMedicaRepository.deleteById(id);
    }
}