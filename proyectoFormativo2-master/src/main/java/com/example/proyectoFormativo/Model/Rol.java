package com.example.proyectoFormativo.Model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "rol")
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID_ROL;

    @Column(name = "NOMBRE_ROL", nullable = false, length = 45)
    private String nombreRol;
}