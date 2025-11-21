package com.example.proyectoFormativo.Services;

import com.example.proyectoFormativo.Dto.Request.ActualizarMascotaRequestDto;
import com.example.proyectoFormativo.Dto.Request.CrearMascotaRequestDto;
import com.example.proyectoFormativo.Dto.Response.MascotaResponseDto;
import com.example.proyectoFormativo.Interface.IMascotaService;
import com.example.proyectoFormativo.Model.*;
import com.example.proyectoFormativo.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MascotaService implements IMascotaService {

    private final IMascotaRepository mascotaRepository;
    private final IUsuarioRepository usuarioRepository; // Tu repo se llama IUsuarioRepository
    private final IHistoriaMedicaRepository historiaMedicaRepository;
    private final ITamanoMascotaRepository tamanoRepo;
    private final ITipoViviendaRepository viviendaRepo;

    @Override
    @Transactional
    public MascotaResponseDto crearMascotaYHistoria(CrearMascotaRequestDto dto, String username) {

        // Usamos findByEmailUsuario (de tu Usuario.java)
        Usuario dueno = usuarioRepository.findByEmailUsuario(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con email: " + username));

        TamanoMascota tamano = tamanoRepo.findById(dto.getTamanoMascotaId())
                .orElseThrow(() -> new RuntimeException("Tamaño no encontrado"));
        TipoVivienda vivienda = viviendaRepo.findById(dto.getTipoViviendaId())
                .orElseThrow(() -> new RuntimeException("Tipo de vivienda no encontrado"));

        Mascota mascota = new Mascota();
        mascota.setNombreMascota(dto.getNombre());
        mascota.setRazaMascota(dto.getRaza());
        mascota.setUsuario(dueno); // <-- Esto ahora funciona
        mascota.setTamanoMascota(tamano);
        mascota.setTipoVivienda(vivienda);
        mascota.setActivo(true);

        Mascota mascotaGuardada = mascotaRepository.save(mascota);

        HistoriaMedica historia = new HistoriaMedica();
        historia.setMascota(mascotaGuardada);
        historia.setFechaCreacion(LocalDate.now());

        historiaMedicaRepository.save(historia);

        return convertirAMascotaResponseDto(mascotaGuardada);
    }

    @Override
    public List<MascotaResponseDto> obtenerMascotasActivas(String username) {
        Usuario dueno = usuarioRepository.findByEmailUsuario(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        List<Mascota> mascotas = mascotaRepository.findByUsuarioAndActivoTrue(dueno);

        return mascotas.stream()
                .map(this::convertirAMascotaResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<MascotaResponseDto> getMascotaById(Integer id, String username) {
        return mascotaRepository.findByIdAndActivoTrue(id)
                // Usamos getEmailUsuario (de tu Usuario.java)
                .filter(mascota -> mascota.getUsuario().getEmailUsuario().equals(username))
                .map(this::convertirAMascotaResponseDto);
    }

    @Override
    @Transactional
    public MascotaResponseDto updateMascota(Integer id, ActualizarMascotaRequestDto dto, String username) {
        Mascota mascota = mascotaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mascota no encontrada"));

        if (!mascota.getUsuario().getEmailUsuario().equals(username)) {
            throw new AccessDeniedException("No tienes permiso para editar esta mascota");
        }

        mascota.setNombreMascota(dto.getNombre());
        mascota.setRazaMascota(dto.getRaza());

        Mascota mascotaActualizada = mascotaRepository.save(mascota);
        return convertirAMascotaResponseDto(mascotaActualizada);
    }

    @Override
    @Transactional
    public void borrarMascotaLogico(Integer id, String username) {
        Mascota mascota = mascotaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mascota no encontrada"));

        if (!mascota.getUsuario().getEmailUsuario().equals(username)) {
            throw new AccessDeniedException("No tienes permiso para borrar esta mascota");
        }

        mascota.setActivo(false);
        mascotaRepository.save(mascota);
    }


    private MascotaResponseDto convertirAMascotaResponseDto(Mascota mascota) {
        MascotaResponseDto dto = new MascotaResponseDto();
        dto.setId(mascota.getId());
        dto.setNombre(mascota.getNombreMascota());
        dto.setRaza(mascota.getRazaMascota());

        if (mascota.getTamanoMascota() != null) {
            dto.setTamano(mascota.getTamanoMascota().getNombre());
        }
        if (mascota.getUsuario() != null) {
            dto.setDueno(mascota.getUsuario().getEmailUsuario());
        }

        return dto;
    }
}