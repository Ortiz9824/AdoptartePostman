package com.example.proyectoFormativo.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "genero")
@Data
public class Genero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idGenero")
    private Integer idGenero; // Coincide con la tabla 'genero'

    @Column(name = "Sexo", length = 20) // Coincide con la tabla 'genero'
    private String sexo;
}