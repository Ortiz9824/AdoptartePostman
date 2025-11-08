package com.example.proyectoFormativo.Repository;

import com.example.proyectoFormativo.Model.Donacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDonacionRepository extends JpaRepository<Donacion, Integer> {
}