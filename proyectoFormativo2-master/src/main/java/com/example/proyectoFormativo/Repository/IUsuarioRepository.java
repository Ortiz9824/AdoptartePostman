package com.example.proyectoFormativo.Repository;

import com.example.proyectoFormativo.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {
    // Método para buscar Usuario por su email (para el Login)
    Optional<Usuario> findByEmailUsuario(String emailUsuario);
}