package com.example.proyectoFormativo.Interface;

import com.example.proyectoFormativo.Model.Mascota;
import java.util.List;
import java.util.Optional;

public interface IMascotaService {
    List<Mascota> getMascotas();
    Optional<Mascota> getMascotaById(Integer id);
    Mascota createMascota(Mascota mascota);
    Mascota updateMascota(Integer id, Mascota mascotaDetails);
    void deleteMascota(Integer id);
}