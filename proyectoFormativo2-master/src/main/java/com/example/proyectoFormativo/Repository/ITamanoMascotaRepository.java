package com.example.proyectoFormativo.Repository;

import com.example.proyectoFormativo.Model.TamanoMascota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITamanoMascotaRepository extends JpaRepository<TamanoMascota, Integer> {


}