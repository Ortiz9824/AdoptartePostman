package com.example.proyectoFormativo.Services;

import com.example.proyectoFormativo.Dto.Request.ActualizarMascotaRequestDto;
import com.example.proyectoFormativo.Dto.Request.CrearMascotaRequestDto;
import com.example.proyectoFormativo.Dto.Response.MascotaResponseDto;
import com.example.proyectoFormativo.Interface.IMascotaService;
import com.example.proyectoFormativo.Model.*; // Importa todos tus modelos
import com.example.proyectoFormativo.Repository.*; // Importa todos tus repos
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor // Inyecta TODOS los repositorios 'final'
public class MascotaService implements IMascotaService {

    // 1. DEPENDENCIAS (inyectadas por @RequiredArgsConstructor)
    private final IMascotaRepository mascotaRepository;
    private final IUserRepository userRepository;
    private final IHistoriaMedicaRepository historiaMedicaRepository;
    private final ITamanoMascotaRepository tamanoRepo;
    private final ITipoViviendaRepository viviendaRepo;

    // Añade aquí cualquier otro Repositorio que necesites (ej. IEspecieRepository)

    /**
     * REQUISITO: "Crear mascotas - crear historial médico"
     * Se asigna al usuario del token y se crea su historial.
     */
    @Override
    @Transactional // Si falla el historial, deshace la creación de la mascota
    public MascotaResponseDto crearMascotaYHistoria(CrearMascotaRequestDto dto, String username) {

        // 1. Buscar al Dueño (User)
        User dueno = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con username: " + username));

        // 2. Buscar Entidades Relacionadas desde los IDs del DTO
        TamanoMascota tamano = tamanoRepo.findById(dto.getTamanoMascotaId())
                .orElseThrow(() -> new RuntimeException("Tamaño no encontrado"));
        TipoVivienda vivienda = viviendaRepo.findById(dto.getTipoViviendaId())
                .orElseThrow(() -> new RuntimeException("Tipo de vivienda no encontrado"));
        // ... (buscar Especie, Estado, etc., si vienen en el DTO)

        // 3. Crear la Entidad Mascota (Mapeo DTO -> Entidad)
        Mascota mascota = new Mascota();
        mascota.setNombreMascota(dto.getNombre());
        mascota.setRazaMascota(dto.getRaza());
        mascota.setUsuario(dueno); // <-- Asignamos el dueño
        mascota.setTamanoMascota(tamano);
        mascota.setTipoVivienda(vivienda);
        mascota.setActivo(true); // <-- REQUISITO: Borrado lógico

        Mascota mascotaGuardada = mascotaRepository.save(mascota);

        // 4. LÓGICA DE NEGOCIO: Crear el Historial Médico
        HistoriaMedica historia = new HistoriaMedica();
        historia.setMascota(mascotaGuardada); // Lo vinculamos
        historia.setFechaCreacion(LocalDate.now());

        historiaMedicaRepository.save(historia);

        // 5. Devolver un DTO (Mapeo Entidad -> DTO)
        return convertirAMascotaResponseDto(mascotaGuardada);
    }

    /**
     * REQUISITO: "Borrado pero lógico"
     * Solo muestra mascotas activas y del usuario logueado.
     */
    @Override
    public List<MascotaResponseDto> obtenerMascotasActivas(String username) {
        User dueno = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Llama al método del repositorio que creaste
        List<Mascota> mascotas = mascotaRepository.findByUsuarioAndActivoTrue(dueno);

        // Convierte la lista de Entidades a una lista de DTOs
        return mascotas.stream()
                .map(this::convertirAMascotaResponseDto)
                .collect(Collectors.toList());
    }

    /**
     * REQUISITO: Seguridad
     * Obtiene una mascota solo si es del usuario logueado y está activa.
     */
    @Override
    public Optional<MascotaResponseDto> getMascotaById(Integer id, String username) {
        // Llama al método del repositorio que creaste
        return mascotaRepository.findByIdAndActivoTrue(id)
                .filter(mascota -> mascota.getUsuario().getUsername().equals(username)) // Valida dueño
                .map(this::convertirAMascotaResponseDto); // Convierte a DTO
    }

    /**
     * REQUISITO: Seguridad
     * Actualiza una mascota solo si es del usuario logueado.
     */
    @Override
    @Transactional
    public MascotaResponseDto updateMascota(Integer id, ActualizarMascotaRequestDto dto, String username) {
        // Busca la mascota (aunque esté inactiva, para permitir reactivarla si quisieras)
        Mascota mascota = mascotaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mascota no encontrada"));

        // Validar propiedad
        if (!mascota.getUsuario().getUsername().equals(username)) {
            throw new AccessDeniedException("No tienes permiso para editar esta mascota");
        }

        // Actualizar campos desde el DTO
        mascota.setNombreMascota(dto.getNombre());
        mascota.setRazaMascota(dto.getRaza());
        // ... (actualizar otros campos: tamaño, vivienda, etc., si es necesario)

        Mascota mascotaActualizada = mascotaRepository.save(mascota);
        return convertirAMascotaResponseDto(mascotaActualizada);
    }

    /**
     * REQUISITO: "Borrado pero lógico"
     * Busca la mascota, valida al dueño y la marca como inactiva.
     */
    @Override
    @Transactional
    public void borrarMascotaLogico(Integer id, String username) {
        Mascota mascota = mascotaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mascota no encontrada"));

        // Validar propiedad
        if (!mascota.getUsuario().getUsername().equals(username)) {
            throw new AccessDeniedException("No tienes permiso para borrar esta mascota");
        }

        // ¡AQUÍ ESTÁ EL BORRADO LÓGICO!
        // No se borra, solo se marca como inactivo
        mascota.setActivo(false);
        mascotaRepository.save(mascota);
    }

    // --- MÉTODO PRIVADO DE MAPEO (Mapper) ---
    // Convierte la Entidad a un DTO de respuesta

    private MascotaResponseDto convertirAMascotaResponseDto(Mascota mascota) {
        MascotaResponseDto dto = new MascotaResponseDto();
        dto.setId(mascota.getId()); // Asumiendo que tu entidad tiene 'id'
        dto.setNombre(mascota.getNombreMascota());
        dto.setRaza(mascota.getRazaMascota());

        // Mapear IDs o Nombres de las relaciones (evita errores nulos)
        if (mascota.getTamanoMascota() != null) {
            dto.setTamano(mascota.getTamanoMascota().getNombre()); // o el campo que tengas
        }
        if (mascota.getUsuario() != null) {
            // Es buena idea no exponer el nombre completo, quizás solo el username
            dto.setDueno(mascota.getUsuario().getUsername());
        }
        // ... otros campos que quieras mostrar

        return dto;
    }
}