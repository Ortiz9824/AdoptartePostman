package com.example.proyectoFormativo.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tipo_vivienda")
@Data // Lombok
public class TipoVivienda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTipo_Vivienda;

    @Column(name = "Tipo", length = 45, unique = true)
    private String tipo;
}