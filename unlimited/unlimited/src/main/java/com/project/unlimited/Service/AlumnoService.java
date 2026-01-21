package com.project.unlimited.Service;

import com.project.unlimited.Model.Alumno;
import com.project.unlimited.Model.Clase;

import java.util.List;

public interface AlumnoService {

    List<Alumno> obtenerTodos();

    Alumno obtenerPorId(Long id);

    Alumno crear(Alumno alumno);

    Alumno actualizar(Long id, Alumno alumno);

    void eliminar(Long id);
}
