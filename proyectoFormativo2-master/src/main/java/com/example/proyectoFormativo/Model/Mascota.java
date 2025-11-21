package com.example.proyectoFormativo.Model;

import jakarta.persistence.*;
import lombok.Data;

@Data // <-- Lombok DEBE crear los setters
@Entity
@Table(name = "mascota")
public class Mascota {

    // ... (campos id, nombreMascota, razaMascota, activo, usuario) ...
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombreMascota;
    private String razaMascota;
    private boolean activo = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    // --- ¡ASEGÚRATE DE TENER ESTAS LÍNEAS! ---

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tamano_mascota_id")
    private TamanoMascota tamanoMascota; // <-- Esto crea setTamanoMascota()

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_vivienda_id")
    private TipoVivienda tipoVivienda; // <-- Esto crea setTipoVivienda()

    // ... (relación con HistoriaMedica)
}