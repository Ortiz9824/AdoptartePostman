package com.example.proyectoFormativo.Controller;

import com.example.proyectoFormativo.Dto.Response.HistoriaMedicaResponseDto;
import com.example.proyectoFormativo.Interface.IHistoriaMedicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/historias-medicas") // Protegido por Rol "VETERINARIO"
public class HistoriaMedicaController {

    @Autowired
    private IHistoriaMedicaService historiaMedicaService;

    /**
     * REQUISITO: "formulario que me traiga información desde la historia médica"
     * Endpoint que llama el frontend al abrir la vista de consulta.
     * Si la lista 'consultas' del DTO está vacía, es la "primera vez".
     */
    @GetMapping("/mascota/{mascotaId}")
    public ResponseEntity<HistoriaMedicaResponseDto> getHistoriaPorMascotaId(@PathVariable Integer mascotaId) {

        HistoriaMedicaResponseDto historia = historiaMedicaService.getHistoriaCompleta(mascotaId);
        return ResponseEntity.ok(historia);
    }
}