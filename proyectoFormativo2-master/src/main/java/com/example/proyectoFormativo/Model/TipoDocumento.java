package com.example.proyectoFormativo.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tipo_documento")
@Data
public class TipoDocumento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTipo_Documento; // Coincide con la tabla 'tipo_documento'

    @Column(name = "Tipo", nullable = false, length = 45) // Coincide con la tabla 'tipo_documento'
    private String tipo;
}