package com.example.proyectoFormativo.Model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "solicitud_adopcion")
@Data
public class SolicitudAdopcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID_SOLICITUD_ADOPCION;

    @Column(name = "FECHA_SOLICITUD", nullable = false)
    private LocalDateTime fechaSolicitud;

    @Column(name = "FECHA_CAMBIO_ESTADO")
    private LocalDateTime fechaCambioEstado;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Formulario_ID_FORMULARIO", nullable = false)
    private Formulario formulario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Estado_solicitud_ID_Estado_solicitud", nullable = false)
    private EstadoSolicitud estadoSolicitud;
}