package com.example.proyectoFormativo.Controller;

import com.example.proyectoFormativo.Interface.IEventoService;
import com.example.proyectoFormativo.Model.Evento;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/eventos")
@RequiredArgsConstructor
public class EventoController {

    // Inyecta la Interfaz
    private final IEventoService eventoService;


    @GetMapping
    public List<Evento> getAllEventos() {
        return eventoService.findAllEventos(); // <-- Nombre de método corregido
    }

    @GetMapping("/{id}")
    public ResponseEntity<Evento> getEventoById(@PathVariable Integer id) {

        Optional<Evento> evento = eventoService.findEventoById(id); // <-- Nombre de método corregido

        return evento.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Evento createEvento(@RequestBody Evento evento) {
        return eventoService.saveEvento(evento); // <-- Nombre de método corregido
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvento(@PathVariable Integer id) {


        if (!eventoService.existsById(id)) { // <-- Método de servicio correcto
            return ResponseEntity.notFound().build();
        }

        eventoService.deleteEventoById(id); // <-- Nombre de método corregido
        return ResponseEntity.ok().build();
    }
}