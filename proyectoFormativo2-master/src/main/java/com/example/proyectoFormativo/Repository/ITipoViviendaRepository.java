package com.example.proyectoFormativo.Repository;

import com.example.proyectoFormativo.Model.TipoVivienda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITipoViviendaRepository extends JpaRepository<TipoVivienda, Integer> {


}