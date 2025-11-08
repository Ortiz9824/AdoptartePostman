package com.example.proyectoFormativo.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "mascota")
@Data
public class Mascota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID_MASCOTA;

    @Column(name = "NOMBRE_MASCOTA", nullable = false, length = 45)
    private String nombreMascota;

    @Column(name = "RAZA_MASCOTA", nullable = false, length = 45)
    private String razaMascota;

    @Column(name = "EDAD_MASCOTA", length = 7)
    private String edadMascota;

    @Column(name = "DESCRIPCION", nullable = false, length = 300)
    private String descripcion;

    @Column(name = "URL_IMAGEN_MASCOTA", length = 255)
    private String urlImagenMascota;

    // --- Relaciones (Claves Foráneas) ---
    // Tu SQL dice que estas clases ya las creaste

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Usuario_ID_USUARIO_ADMIN_REFUGIO", nullable = false)
    private Usuario adminRefugio; // La entidad Usuario que ya creaste

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Especie_Mascota_ID_Especie_Mascota", nullable = false)
    private EspecieMascota especie; // Ya existe en tu carpeta Model

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Tamaño_Mascota_ID_Tamaño_Mascota", nullable = false)
    private TamañoMascota tamaño; // Ya existe en tu carpeta Model

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Estado_Mascota_ID_Estado_Mascota", nullable = false)
    private EstadoMascota estado; // Ya existe en tu carpeta Model

    // Omitimos las relaciones con Evaluacion y Solicitud por ahora para simplificar
}