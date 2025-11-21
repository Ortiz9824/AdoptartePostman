package com.example.proyectoFormativo.Model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.time.LocalDate;
import java.time.LocalDateTime; // Cambiamos a LocalDateTime para incluir hora si es necesario (como en la BD)
import java.util.List;

@Data
@Entity
@Table(name = "historia_medica")
@SQLDelete(sql = "UPDATE historia_medica SET activo = false WHERE id_historia_medica = ?")
@Where(clause = "activo = true")
public class HistoriaMedica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_HISTORIA_MEDICA") // Alineado con la PK de la tabla SQL
    private Long id;


    @Column(name = "FECHA_EVALUACION", nullable = false)
    private LocalDateTime fechaEvaluacion;

    @Column(name = "DESCRIPCION", length = 300)
    private String descripcion;

    @Column(name = "RECOMENDACIONES", length = 200)
    private String recomendaciones;

    @Column(name = "ACTIVO", nullable = false)
    private Boolean activo = true; // Por defecto, es TRUE al crear


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Mascota_ID_MASCOTA", nullable = false, unique = true)
    private Mascota mascota;

    @OneToMany(mappedBy = "historiaMedica", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ConsultaMedica> consultas;

    public void setFechaCreacion(LocalDate now) {
    }
}