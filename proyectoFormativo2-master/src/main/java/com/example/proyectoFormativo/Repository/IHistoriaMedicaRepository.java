package com.example.proyectoFormativo.Repository;


import com.example.proyectoFormativo.Model.HistoriaMedica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IHistoriaMedicaRepository extends JpaRepository<HistoriaMedica, Long> {


    Optional<HistoriaMedica> findByMascota_Id(Long mascotaId);

}