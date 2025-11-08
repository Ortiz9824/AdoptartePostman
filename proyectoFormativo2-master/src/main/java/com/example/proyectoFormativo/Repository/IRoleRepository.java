package com.example.proyectoFormativo.Repository;

import com.example.proyectoFormativo.Model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRoleRepository extends JpaRepository<Rol, Integer> {
    // Método para buscar Rol por su nombre (para el RoleSeeder)
    Optional<Rol> findByNombreRol(String nombreRol);
}