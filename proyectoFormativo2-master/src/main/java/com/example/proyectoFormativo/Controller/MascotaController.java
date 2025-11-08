package com.example.proyectoFormativo.Controller;

import com.example.proyectoFormativo.Interface.IMascotaService;
import com.example.proyectoFormativo.Model.Mascota;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mascotas") // URL base para mascotas
@RequiredArgsConstructor
public class MascotaController {

    private final IMascotaService mascotaService; // Inyecta la INTERFAZ

    @GetMapping
    public List<Mascota> getAllMascotas() {
        return mascotaService.getMascotas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mascota> getMascotaById(@PathVariable Integer id) {
        return mascotaService.getMascotaById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mascota createMascota(@RequestBody Mascota mascota) {
        return mascotaService.createMascota(mascota);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mascota> updateMascota(@PathVariable Integer id, @RequestBody Mascota mascotaDetails) {
        try {
            return ResponseEntity.ok(mascotaService.updateMascota(id, mascotaDetails));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMascota(@PathVariable Integer id) {
        mascotaService.deleteMascota(id);
        return ResponseEntity.ok().build();
    }
}