package com.example.proyectoFormativo.Model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Set;

@Data
@Entity
@Table(name = "rol") // Nombre de la tabla de roles
public class RolService  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID_ROL; // Coincide con el "Rol_ID_ROL" de tu clase Usuario

    @Column(name = "NOMBRE_ROL", length = 45, nullable = false, unique = true)
    private String nombreRol;

    // Relación inversa: Un rol puede tener muchos usuarios
    @OneToMany(mappedBy = "rol")
    private Set<Usuario> usuarios;

    // Método que necesita Spring Security (desde Usuario)
    public String getNombreRol() {
        return nombreRol;
    }
}