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
    public void run(String... args) {
        List<String> roles = Arrays.asList("ADMIN", "BIBLIOTECARIO", "LECTOR");

        for (String roleName : roles) {
            roleRepository.findByName(roleName).ifPresentOrElse(
                    existing -> System.out.println("Rol existente: " + existing.getName()),
                    () -> {
                        Rol role = new Rol();
                        role.setName(roleName);
                        roleRepository.save(role);
                        System.out.println("Rol creado: " + roleName);
                    }
            );
        }
    }
}
