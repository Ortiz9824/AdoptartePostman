package com.example.proyectoFormativo.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tamaño_mascota")
@Data // Lombok
public class TamañoMascota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID_Tamaño_Mascota;

    @Column(name = "Tamaño", length = 45)
    private String tamaño;
}