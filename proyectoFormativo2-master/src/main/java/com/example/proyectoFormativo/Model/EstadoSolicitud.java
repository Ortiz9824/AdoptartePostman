package com.example.proyectoFormativo.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "estado_solicitud")
@Data
public class EstadoSolicitud {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Estado_solicitud")
    private Integer ID_Estado_solicitud;

    @Column(name = "Estado", length = 45)
    private String estado;
}