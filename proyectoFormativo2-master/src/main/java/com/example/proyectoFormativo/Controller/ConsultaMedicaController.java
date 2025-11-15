package com.example.proyectoFormativo.Controller;

import com.example.proyectoFormativo.Dto.Request.CrearConsultaRequestDto;
import com.example.proyectoFormativo.Dto.Response.ConsultaResponseDto;
import com.example.proyectoFormativo.Interface.IConsultaMedicaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication; // Para saber qué vet está logueado
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/consultas-medicas") // Protegido por Rol "VETERINARIO"
public class ConsultaMedicaController {

    @Autowired
    private IConsultaMedicaService consultaService;

    /**
     * REQUISITO: "Editar historial registro" (Añadir un nuevo registro)
     * Endpoint que se llama al guardar el formulario de consulta.
     */
    @PostMapping
    public ResponseEntity<ConsultaResponseDto> crearConsulta(
            @Valid @RequestBody CrearConsultaRequestDto dto,
            Authentication authentication // Obtenemos al veterinario del token
    ) {
        String vetUsername = authentication.getName();
        ConsultaResponseDto nuevaConsulta = consultaService.crearConsulta(dto, vetUsername);
        return new ResponseEntity<>(nuevaConsulta, HttpStatus.CREATED);
    }
}