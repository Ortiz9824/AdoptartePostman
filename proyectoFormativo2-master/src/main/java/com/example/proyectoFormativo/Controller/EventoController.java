package com.example.proyectoFormativo.Controller;

import com.example.proyectoFormativo.Model.Evento;
import com.example.proyectoFormativo.Services.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/eventos")
public class EventoController {

    @Autowired
    private EventoService eventoService; // Asegúrate de haber creado EventoService

    // GET: Obtener todos los eventos
    @GetMapping
    public List<Evento> getAllEventos() {
        return eventoService.getEventos();
    }

    // GET: Obtener un evento por ID
    @GetMapping("/{id}")
    public ResponseEntity<Evento> getEventoById(@PathVariable Integer id) {
        Optional<Evento> evento = eventoService.getEvento(id);
        return evento.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST: Crear un nuevo evento
    @PostMapping
    public Evento createEvento(@RequestBody Evento evento) {
        return eventoService.saveEvento(evento);
    }

    // DELETE: Eliminar un evento
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvento(@PathVariable Integer id) {
        if (eventoService.getEvento(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        eventoService.deleteEvento(id);
        return ResponseEntity.ok().build();
    }
}