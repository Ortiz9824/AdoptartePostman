package com.example.proyectoFormativo.Services;

import com.example.proyectoFormativo.Interface.IMascotaService;
import com.example.proyectoFormativo.Model.Mascota;
import com.example.proyectoFormativo.Repository.IMascotaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor // Para inyectar el repositorio
public class MascotaService implements IMascotaService {

    private final IMascotaRepository mascotaRepository;

    @Override
    public List<Mascota> getMascotas() {
        return mascotaRepository.findAll();
    }

    @Override
    public Optional<Mascota> getMascotaById(Integer id) {
        return mascotaRepository.findById(id);
    }

    @Override
    public Mascota createMascota(Mascota mascota) {
        // Aquí podrías añadir lógica, como poner estado "Disponible" (ID 1) por defecto
        // EstadoMascota disponible = estadoRepository.findById(1).get();
        // mascota.setEstado(disponible);
        return mascotaRepository.save(mascota);
    }

    @Override
    public Mascota updateMascota(Integer id, Mascota mascotaDetails) {
        // Busca la mascota existente
        return mascotaRepository.findById(id)
                .map(mascota -> {
                    // Actualiza los campos
                    mascota.setNombreMascota(mascotaDetails.getNombreMascota());
                    mascota.setRazaMascota(mascotaDetails.getRazaMascota());
                    mascota.setEdadMascota(mascotaDetails.getEdadMascota());
                    mascota.setDescripcion(mascotaDetails.getDescripcion());
                    mascota.setEstado(mascotaDetails.getEstado());
                    mascota.setEspecie(mascotaDetails.getEspecie());
                    mascota.setTamaño(mascotaDetails.getTamaño());
                    mascota.setUrlImagenMascota(mascotaDetails.getUrlImagenMascota());

                    return mascotaRepository.save(mascota); // Guarda la mascota actualizada
                }).orElseThrow(() -> new RuntimeException("Mascota no encontrada con ID: " + id));
    }

    @Override
    public void deleteMascota(Integer id) {
        mascotaRepository.deleteById(id);
    }
}