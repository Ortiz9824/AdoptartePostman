package com.example.proyectoFormativo.Services;

import com.example.proyectoFormativo.Interface.IDonacionService;
import com.example.proyectoFormativo.Model.Donacion;
import com.example.proyectoFormativo.Repository.IDonacionRepository;
import lombok.RequiredArgsConstructor; // Importa esto
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor // <-- Esto inyecta el repositorio (como en tu AuthService)
public class DonacionService implements IDonacionService {

    private final IDonacionRepository donacionRepository; // <-- Decláralo como 'final'

    @Override
    public List<Donacion> getDonaciones() {
        return donacionRepository.findAll();
    }

    @Override
    public Donacion saveDonacion(Donacion donacion) {
        // Añadimos la lógica de negocio: poner la fecha actual
        donacion.setFechaDonacion(LocalDateTime.now());
        return donacionRepository.save(donacion);
    }
}