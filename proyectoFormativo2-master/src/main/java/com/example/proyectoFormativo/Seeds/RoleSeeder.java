package com.example.proyectoFormativo.Seeds;

import com.example.proyectoFormativo.Model.Rol;
import com.example.proyectoFormativo.Repository.IRolRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Component
public class RoleSeeder implements CommandLineRunner {

    private final IRolRepository roleRepository;

    public RoleSeeder(IRolRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        // Asegúrate que estos nombres coincidan con tu lógica (CLIENTE, VETERINARIO, etc.)
        List<String> roles = Arrays.asList("CLIENTE", "VETERINARIO", "ADMIN");

        for (String roleName : roles) {

            // ¡ARREGLO 1! Usa el método correcto
            roleRepository.findByNombreRol(roleName).ifPresentOrElse(

                    // Si existe, solo imprime
                    existing -> System.out.println("Rol existente: " + existing.getNombreRol()),

                    // Si no existe, créalo
                    () -> {
                        Rol role = new Rol();
                        // ¡ARREGLO 2! Usa el setter correcto
                        role.setNombreRol(roleName);
                        roleRepository.save(role);
                        System.out.println("Rol creado: " + roleName);
                    }
            );
        }
    }
}
