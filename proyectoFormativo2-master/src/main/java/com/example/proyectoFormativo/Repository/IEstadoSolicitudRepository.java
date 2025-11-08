package com.example.proyectoFormativo.Repository;

import com.example.proyectoFormativo.Model.EstadoSolicitud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEstadoSolicitudRepository extends JpaRepository<EstadoSolicitud, Integer> {
}