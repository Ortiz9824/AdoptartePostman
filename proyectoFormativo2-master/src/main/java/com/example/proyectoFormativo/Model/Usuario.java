package com.example.proyectoFormativo.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuario")
public class Usuario implements UserDetails { // Implementamos UserDetails

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID_USUARIO;

    @Column(name = "NOMBRE_USUARIO", nullable = false, length = 45)
    private String nombreUsuario;

    @Column(name = "APELLIDO_USUARIO", length = 45)
    private String apellidoUsuario;

    @Column(name = "DOCUMENTO_USUARIO", nullable = false, length = 12)
    private String documentoUsuario;

    @Column(name = "EMAIL_USUARIO", nullable = false, length = 100, unique = true)
    private String emailUsuario;

    @Column(name = "PASSWORD_USUARIO", length = 255)
    private String passwordUsuario;

    @Column(name = "DIRECCION_USUARIO", length = 45)
    private String direccionUsuario;

    @Column(name = "TELEFONO_USUARIO", length = 10)
    private String telefonoUsuario;

    @Column(name = "TARJETA_PROFESIONAL", length = 45)
    private String tarjetaProfesional;

    // --- Relaciones ---
    @ManyToOne(fetch = FetchType.EAGER) // EAGER para que cargue el rol con el usuario
    @JoinColumn(name = "Rol_ID_ROL", nullable = false)
    private Rol rol;

    @ManyToOne
    @JoinColumn(name = "Genero_idGenero")
    private Genero genero; // Debes crear la entidad Genero

    @ManyToOne
    @JoinColumn(name = "Tipo_Documento_idTipo_Documento", nullable = false)
    private TipoDocumento tipoDocumento; // Debes crear la entidad TipoDocumento

    // --- Métodos de UserDetails ---
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Devolvemos el nombre del ROL (ej. "Adoptante", "Administrador")
        return List.of(new SimpleGrantedAuthority(rol.getNombreRol()));
    }

    @Override
    public String getPassword() {
        return passwordUsuario;
    }

    @Override
    public String getUsername() {
        return emailUsuario; // Usamos el email como username
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}