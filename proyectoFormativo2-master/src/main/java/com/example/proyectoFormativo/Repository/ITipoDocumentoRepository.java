package com.example.proyectoFormativo.Repository;

import com.example.proyectoFormativo.Model.TipoDocumento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITipoDocumentoRepository extends JpaRepository<TipoDocumento, Integer> {
}