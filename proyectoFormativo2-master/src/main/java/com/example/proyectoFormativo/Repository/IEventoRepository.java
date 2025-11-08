package com.example.proyectoFormativo.Repository;

import com.example.proyectoFormativo.Model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEventoRepository extends JpaRepository<Evento, Integer> {
}