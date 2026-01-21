package com.project.unlimited.Service;

import com.project.unlimited.Model.Alumno;
import com.project.unlimited.Model.Clase;

import java.util.List;

public interface ClaseService {

    List<Clase> obtenerTodas();

    Clase obtenerPorId(Long id);

    Clase crear(Clase clase);

    Clase actualizar(Long id, Clase clase);

    void eliminar(Long id);

    List<Alumno> obtenerAlumnosDeClase(Long claseId);

    void agregarAlumno(Long claseId, Long alumnoId);

    void quitarAlumno(Long claseId, Long alumnoId);
}
