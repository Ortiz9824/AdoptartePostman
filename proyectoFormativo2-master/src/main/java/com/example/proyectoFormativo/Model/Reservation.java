package com.example.proyectoFormativo.Model;

import jakarta.persistence.*; // <-- Importante que sea jakarta
import lombok.Data;
import org.apache.catalina.User;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "reservations") // Nombre de la tabla en la base de datos
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // <-- Debe ser Long, para coincidir con JpaRepository<..., Long>

    @Column(name = "fecha_hora", nullable = false)
    private LocalDateTime fechaHora;

    @Column(name = "estado", length = 50)
    private String estado; // Ej: "PENDIENTE", "CONFIRMADA", "CANCELADA"

    @Column(name = "motivo", columnDefinition = "TEXT")
    private String motivo;

    // Relación con el Usuario (quién hace la reserva)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private User usuario;

    // Relación con la Mascota (para quién es la reserva)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mascota_id", nullable = false)
    private Mascota mascota;

    // Puedes añadir una relación al Veterinario si sabes quién atenderá
    // @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "veterinario_id")
    // private User veterinario;
}