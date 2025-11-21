package com.example.proyectoFormativo.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "formulario")
@Data
public class Formulario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_formulario")
    private Integer ID_FORMULARIO;

    @Column(name = "CIUDAD", nullable = false, length = 45)
    private String ciudad;

    @Column(name = "LOCALIDAD", nullable = false, length = 45)
    private String localidad;

    @Column(name = "BARRIO", nullable = false, length = 45)
    private String barrio;

    @Column(name = "OCUPACION", nullable = false, length = 45)
    private String ocupacion;

    @Column(name = "NUM_DE_PERSONAS_CONVIVE", nullable = false, length = 45)
    private String numDePersonasConvive;

    @Column(name = "OBSERVACIONES", length = 200)
    private String observaciones;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Usuario_ID_USUARIO_ADOPTANTE", nullable = false)
    private Usuario adoptante; // Asume que ya creaste la entidad Usuario

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Tipo_Vivienda_idTipo_Vivienda", nullable = false)
    private TipoVivienda tipoVivienda; // Asume que ya creaste la entidad TipoVivienda
}