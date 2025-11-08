package com.example.proyectoFormativo.Controller;

import com.example.proyectoFormativo.Model.Donacion;
import com.example.proyectoFormativo.Services.DonacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/donaciones")
public class DonacionController {

    @Autowired
    private DonacionService donacionService; // Asegúrate de haber creado DonacionService

    // GET: Obtener todas las donaciones
    @GetMapping
    public List<Donacion> getAllDonaciones() {
        return donacionService.getDonaciones();
    }

    // POST: Registrar una nueva donación
    @PostMapping
    public Donacion createDonacion(@RequestBody Donacion donacion) {
        return donacionService.saveDonacion(donacion);
    }
}