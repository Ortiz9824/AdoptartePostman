package com.example.proyectoFormativo.Services;

import com.example.proyectoFormativo.Model.EstadoSolicitud;
import com.example.proyectoFormativo.Model.SolicitudAdopcion;
import com.example.proyectoFormativo.Repository.IEstadoSolicitudRepository;
import com.example.proyectoFormativo.Repository.ISolicitudAdopcionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SolicitudAdopcionService {

    @Autowired
    private ISolicitudAdopcionRepository solicitudRepository;

    @Autowired
    private IEstadoSolicitudRepository estadoRepository;

    public List<SolicitudAdopcion> getSolicitudes() {
        return solicitudRepository.findAll();
    }

    public Optional<SolicitudAdopcion> getSolicitud(Integer id) {
        return solicitudRepository.findById(id);
    }

    public SolicitudAdopcion saveSolicitud(SolicitudAdopcion solicitud) {
        // Lógica de negocio: Asignar estado "Pendiente" (ID 4) al crear
        solicitud.setFechaSolicitud(LocalDateTime.now());

        EstadoSolicitud pendiente = estadoRepository.findById(4)
                .orElseThrow(() -> new RuntimeException("Estado 'Pendiente' (ID 4) no encontrado en la BD"));
        solicitud.setEstadoSolicitud(pendiente);

        return solicitudRepository.save(solicitud);
    }

    // Servicio para que un Admin cambie el estado
    public SolicitudAdopcion cambiarEstado(Integer solicitudId, Integer nuevoEstadoId) {
        SolicitudAdopcion solicitud = solicitudRepository.findById(solicitudId)
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada con ID: " + solicitudId));

        EstadoSolicitud nuevoEstado = estadoRepository.findById(nuevoEstadoId)
                .orElseThrow(() -> new RuntimeException("Estado no encontrado con ID: " + nuevoEstadoId));

        solicitud.setEstadoSolicitud(nuevoEstado);
        solicitud.setFechaCambioEstado(LocalDateTime.now());
        return solicitudRepository.save(solicitud);
    }
}