package com.example.proyectoFormativo.Repository;

import com.example.proyectoFormativo.Model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRolRepository extends JpaRepository<Rol, Integer> {


    Optional<Rol> findByNombreRol(String nombreRol);

}