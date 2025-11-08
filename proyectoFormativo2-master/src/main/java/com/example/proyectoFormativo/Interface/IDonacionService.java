package com.example.proyectoFormativo.Interface;

import com.example.proyectoFormativo.Model.Donacion;
import java.util.List;

public interface IDonacionService {

    List<Donacion> getDonaciones();

    Donacion saveDonacion(Donacion donacion);

    // Aquí puedes añadir más métodos en el futuro (como getById, delete, etc.)
}