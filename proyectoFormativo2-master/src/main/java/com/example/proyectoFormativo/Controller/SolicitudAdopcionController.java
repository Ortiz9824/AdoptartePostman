package com.example.proyectoFormativo.Controller;

import com.example.proyectoFormativo.Model.SolicitudAdopcion;
import com.example.proyectoFormativo.Services.SolicitudAdopcionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/solicitudes")
public class SolicitudAdopcionController {

    @Autowired
    private SolicitudAdopcionService solicitudService; // Asegúrate de haber creado SolicitudAdopcionService

    // GET: Obtener todas las solicitudes
    @GetMapping
    public List<SolicitudAdopcion> getAllSolicitudes() {
        return solicitudService.getSolicitudes();
    }

    // GET: Obtener una solicitud por ID
    @GetMapping("/{id}")
    public ResponseEntity<SolicitudAdopcion> getSolicitudById(@PathVariable Integer id) {
        Optional<SolicitudAdopcion> solicitud = solicitudService.getSolicitud(id);
        return solicitud.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST: Crear una nueva solicitud (la inicia un adoptante)
    @PostMapping
    public SolicitudAdopcion createSolicitud(@RequestBody SolicitudAdopcion solicitud) {
        // El servicio debe poner el estado por defecto "Pendiente" y la fecha
        return solicitudService.saveSolicitud(solicitud);
    }

    // PUT: Actualizar el estado de una solicitud (lo hace un Admin)
    @PutMapping("/{id}/estado")
    public ResponseEntity<SolicitudAdopcion> cambiarEstadoSolicitud(@PathVariable Integer id, @RequestBody Map<String, Integer> payload) {
        try {
            // El JSON debe ser: { "idEstado": 2 }  (para "Rechazado", por ejemplo)
            Integer nuevoEstadoId = payload.get("idEstado");
            SolicitudAdopcion solicitud = solicitudService.cambiarEstado(id, nuevoEstadoId);
            return ResponseEntity.ok(solicitud);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}