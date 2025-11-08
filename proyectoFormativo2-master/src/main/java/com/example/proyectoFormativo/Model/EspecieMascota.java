package com.example.proyectoFormativo.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "especie_mascota")
@Data // Lombok
public class EspecieMascota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID_Especie_Mascota;

    @Column(name = "Especie", nullable = false, length = 45)
    private String especie;
}