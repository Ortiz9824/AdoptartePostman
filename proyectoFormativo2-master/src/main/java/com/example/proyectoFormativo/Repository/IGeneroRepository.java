package com.example.proyectoFormativo.Repository;

import com.example.proyectoFormativo.Model.Genero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IGeneroRepository extends JpaRepository<Genero, Integer> {
}