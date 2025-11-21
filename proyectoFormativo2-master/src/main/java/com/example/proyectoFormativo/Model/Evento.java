package com.example.proyectoFormativo.Model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data // <-- Añade getters, setters, toString, etc.
@Entity
@Table(name = "eventos") // O como se llame tu tabla en la base de datos
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_EVENTO")
    private Integer id; // Coincide con el <Evento, Integer> de tu repositorio

    @Column(name = "nombre_evento", nullable = false)
    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    private LocalDateTime fechaInicio;

    private String ubicacion;

    // Puedes añadir más campos (ej: capacidad, organizador, etc.)
}