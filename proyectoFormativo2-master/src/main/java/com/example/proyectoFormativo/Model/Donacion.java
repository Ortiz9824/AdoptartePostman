package com.example.proyectoFormativo.Model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "donacion")
@Data
public class Donacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID_DONACION;

    @Column(name = "VALOR_DONACION", nullable = false, precision = 8, scale = 2)
    private BigDecimal valorDonacion;

    @Column(name = "FECHA_DONACION", nullable = false)
    private LocalDateTime fechaDonacion;
}