package com.example.proyectoFormativo.Services;

import com.example.proyectoFormativo.Model.Evento;
import com.example.proyectoFormativo.Repository.IEventoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor // <-- 2. Inyecta el repositorio automáticamente
public class EventoService { // <-- 3. Renombrado a singular: "EventoService"


    private final IEventoRepository eventoRepository;


    public Evento crearEvento(Evento evento) {
        // Aquí puedes añadir lógica de validación
        return eventoRepository.save(evento);
    }


    public List<Evento> obtenerTodosLosEventos() {
        return eventoRepository.findAll();
    }


}