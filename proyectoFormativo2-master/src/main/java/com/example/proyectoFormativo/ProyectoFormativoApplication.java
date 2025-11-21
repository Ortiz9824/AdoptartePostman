package com.example.proyectoFormativo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ProyectoFormativoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProyectoFormativoApplication.class, args);
	}


}
