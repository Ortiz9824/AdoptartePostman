package com.example.proyectoFormativo.Repository;

import com.example.proyectoFormativo.Model.ConsultaMedica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IConsultaMedicaRepository extends JpaRepository<ConsultaMedica, Integer> {

    // ¡Eso es todo!
    // Al extender JpaRepository, Spring te da automáticamente todos
    // los métodos CRUD para la entidad "ConsultaMedica".

    // <ConsultaMedica, Integer> significa:
    // 1. "Voy a manejar la entidad ConsultaMedica".
    // 2. "El tipo de dato de su ID (@Id) es Integer".
}