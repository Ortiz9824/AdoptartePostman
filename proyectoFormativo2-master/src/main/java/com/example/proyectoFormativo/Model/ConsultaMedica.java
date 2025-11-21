package com.example.proyectoFormativo.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "consultas_medicas")
public class ConsultaMedica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha_consulta", nullable = false)
    private LocalDateTime fechaConsulta;

    @Column(name = "sintomas", columnDefinition = "TEXT")
    private String sintomas;

    @Column(name = "diagnostico", columnDefinition = "TEXT")
    private String diagnostico;

    @Column(name = "tratamiento", columnDefinition = "TEXT")
    private String tratamiento;

    @Column(name = "observaciones", columnDefinition = "TEXT")
    private String observaciones;

    // --- RELACIONES ---

    /**
     * Relación con el Historial Médico.
     * Muchas consultas pertenecen a UN historial.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "historia_medica_id", nullable = false)
    private HistoriaMedica historiaMedica;

    /**
     * Relación con el Veterinario (User).
     * Muchos consultas son realizadas por UN veterinario.
     * Asumimos que el Veterinario es un tipo de 'User'.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "veterinario_id", nullable = false)
    private Usuario veterinario; // <-- Esto ahora funciona porque el import es correcto
}