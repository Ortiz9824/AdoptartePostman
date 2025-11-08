package com.example.proyectoFormativo.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "estado_mascota")
@Data // Lombok
public class EstadoMascota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID_Estado_Mascota;

    @Column(name = "Estado", length = 45)
    private String estado;
}