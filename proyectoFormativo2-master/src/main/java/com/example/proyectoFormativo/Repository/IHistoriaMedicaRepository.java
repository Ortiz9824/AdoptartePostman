package com.example.proyectoFormativo.Repository;

import com.example.proyectoFormativo.Model.HistoriaMedica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IHistoriaMedicaRepository extends JpaRepository<HistoriaMedica, Integer> {

    /**
     * Busca un historial médico usando el ID de la mascota.
     * Esto es crucial para la vista del veterinario.
     */
    Optional<HistoriaMedica> findByMascota_Id(Integer mascotaId);

}