package com.example.proyectoFormativo.Repository;

import com.example.proyectoFormativo.Model.SolicitudAdopcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISolicitudAdopcionRepository extends JpaRepository<SolicitudAdopcion, Integer> {
}