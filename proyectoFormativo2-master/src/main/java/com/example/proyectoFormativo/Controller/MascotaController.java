package com.example.proyectoFormativo.Controller;

// 1. Imports de tus clases (DTOs, Interfaces)
import com.example.proyectoFormativo.Interface.IMascotaService;
import com.example.proyectoFormativo.Dto.Request.CrearMascotaRequestDto;
import com.example.proyectoFormativo.Dto.Response.MascotaResponseDto;

// 2. Imports de Lombok (para @RequiredArgsConstructor)
import lombok.RequiredArgsConstructor;

// 3. Imports de Spring (para la API y Seguridad)
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

// 4. Imports de Java y Jakarta (para @Valid)
import jakarta.validation.Valid;
import java.util.List;

/**
 * Este es el controlador para el CRUD de Mascotas.
 * Se encarga de recibir las peticiones HTTP y llamar al servicio.
 */
@RestController
@RequestMapping("/api/v1/mascotas")
@RequiredArgsConstructor
public class MascotaController {

    private final IMascotaService mascotaService;

    /**
     * Endpoint para CREAR una mascota.
     * El dueño se obtiene del token de autenticación.
     */
    @PostMapping
    public ResponseEntity<MascotaResponseDto> createMascota(
            @Valid @RequestBody CrearMascotaRequestDto mascotaDto,
            Authentication authentication
    ) {
        // 1. Extraemos el username (email) del token
        String username = authentication.getName();

        // 2. Llamamos al servicio (con el nombre de método corregido)
        // (Este es el método que existe en tu IMascotaService)
        MascotaResponseDto nuevaMascota = mascotaService.crearMascotaYHistoria(mascotaDto, username);

        return new ResponseEntity<>(nuevaMascota, HttpStatus.CREATED);
    }

    /**
     * Endpoint para OBTENER TODAS las mascotas
     */
    @GetMapping
    public ResponseEntity<List<MascotaResponseDto>> getAllMascotas(Authentication authentication) {
        // Obtenemos el username para filtrar las mascotas
        String username = authentication.getName();

        // ¡ESTA ES LA LÍNEA COMPLETA QUE FALTA EN TU IMAGEN!
        // (Este es el método que existe en tu IMascotaService)
        List<MascotaResponseDto> mascotas = mascotaService.obtenerMascotasActivas(username);

        return ResponseEntity.ok(mascotas);
    }
}