package com.example.proyectoFormativo.Repository;

import com.example.proyectoFormativo.Model.Formulario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFormularioRepository extends JpaRepository<Formulario, Integer> {
}