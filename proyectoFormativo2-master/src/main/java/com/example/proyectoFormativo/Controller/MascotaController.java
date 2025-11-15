package com.example.proyectoFormativo.Controller;

import com.example.proyectoFormativo.Interface.IMascotaService; // <-- LA IMPORTA
import com.example.proyectoFormativo.Dto.Request.*; // <-- Importa tus DTOs
import com.example.proyectoFormativo.Dto.Response.*; // <-- Importa tus DTOs

// ... otros imports
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

@RestController
@RequestMapping("/api/v1/mascotas")
public class MascotaController { // <-- SOLO LA CLASE CONTROLLER

    @Autowired
    private IMascotaService mascotaService;

    // ... (Todo el código del controlador que vimos antes)

    @PostMapping
    public ResponseEntity<MascotaResponseDto> createMascota(
            @Valid @RequestBody CrearMascotaRequestDto mascotaDto,
            Authentication authentication
    ) {
        // ...
    }


}

