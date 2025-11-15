package com.example.proyectoFormativo.Model;

// 👇 ¡TAMBIÉN USA JAKARTA!
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "historias_medicas")
public class HistoriaMedica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDate fechaCreacion;

    // --- RELACIONES ---

    // Relación con la Mascota (Dueña de la historia)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mascota_id", referencedColumnName = "id", nullable = false, unique = true)
    private Mascota mascota;

    // Relación con las consultas (Un historial tiene muchas consultas)
    @OneToMany(mappedBy = "historiaMedica", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ConsultaMedica> consultas;

}