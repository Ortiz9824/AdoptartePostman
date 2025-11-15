package com.example.proyectoFormativo.Model;

// 👇 ¡CAMBIO AQUÍ! De 'javax' a 'jakarta'
import jakarta.persistence.*;
import lombok.Data;
import org.apache.catalina.User;

@Data
@Entity
@Table(name = "mascotas")
public class Mascota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // Coincide con JpaRepository<..., Integer>

    @Column(name = "nombre_mascota", length = 100, nullable = false)
    private String nombreMascota;

    @Column(name = "raza_mascota", length = 100)
    private String razaMascota;

    @Column(name = "activo")
    private boolean activo = true; // Para el borrado lógico

    // --- RELACIONES ---

    // Relación con el Dueño (User)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    public User usuario;

    // Relación con Tamaño
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tamano_mascota_id")
    private TamanoMascota tamanoMascota;

    // Relación con Tipo de Vivienda
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_vivienda_id")
    private TipoVivienda tipoVivienda;

    // ¡ESTA ES LA LÍNEA QUE DA EL OTRO ERROR!
    // Relación con su Historia Médica
    @OneToOne(mappedBy = "mascota", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private HistoriaMedica historiaMedica;

    // ... (Añade relaciones con Especie, EstadoMascota, etc. si las tienes)
}