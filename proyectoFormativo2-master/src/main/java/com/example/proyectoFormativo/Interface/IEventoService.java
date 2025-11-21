package com.example.proyectoFormativo.Interface;

import com.example.proyectoFormativo.Model.Evento;
import java.util.List;
import java.util.Optional;

public interface IEventoService {

    List<Evento> findAllEventos();

    Optional<Evento> findEventoById(Integer id);

    Evento saveEvento(Evento evento);

    void deleteEventoById(Integer id);

    boolean existsById(Integer id); // Para la validación de borrado
}