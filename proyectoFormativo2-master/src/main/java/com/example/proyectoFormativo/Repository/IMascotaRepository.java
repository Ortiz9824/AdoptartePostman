package com.example.proyectoFormativo.Repository;

import com.example.proyectoFormativo.Model.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMascotaRepository extends JpaRepository<Mascota, Integer> {
    // No necesitas escribir nada aquí
}