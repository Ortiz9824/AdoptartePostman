package com.example.proyectoFormativo.Model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tamano_mascota")
public class TamanoMascota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre", length = 50, unique = true, nullable = false)
    private String nombre; // Ej: "Pequeño", "Mediano", "Grande"

    // Aquí puedes tener una @OneToMany List<Mascota> si quieres,
    // pero no es necesario para que Mascota.java funcione.
}