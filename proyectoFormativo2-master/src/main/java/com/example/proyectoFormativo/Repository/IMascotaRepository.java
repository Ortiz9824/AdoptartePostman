package com.example.proyectoFormativo.Repository;

import com.example.proyectoFormativo.Model.User;
import com.example.proyectoFormativo.Model.Mascota; // <-- ¡ESTA LÍNEA FALTABA!
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository; // Añade esta anotación
import java.util.List;
import java.util.Optional;

@Repository // Es una buena práctica declarar la anotación
public interface IMascotaRepository extends JpaRepository<Mascota, Integer> {

    // NECESARIO PARA EL SERVICIO

    // Buscar todas las mascotas ACTIVAS de un usuario específico
    List<Mascota> findByUsuarioAndActivoTrue(User usuario);

    // Buscar una mascota por ID solo si está ACTIVA
    Optional<Mascota> findByIdAndActivoTrue(Integer id);
}