package com.project.unlimited.Service;

import com.project.unlimited.Model.Alumno;
import com.project.unlimited.Model.Clase;
import com.project.unlimited.Repository.AlumnoRepository;
import com.project.unlimited.Repository.ClaseRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClaseServiceImpl implements ClaseService {

    private final ClaseRepository claseRepository;
    private final AlumnoRepository alumnoRepository;

    public ClaseServiceImpl(ClaseRepository claseRepository, AlumnoRepository alumnoRepository) {
        this.claseRepository = claseRepository;
        this.alumnoRepository = alumnoRepository;
    }

    @Override
    public List<Clase> obtenerTodas() {
        return claseRepository.findAll();
    }

    @Override
    public Clase obtenerPorId(Long id) {
        return claseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Clase no encontrada"));
    }

    @Override
    public Clase crear(Clase clase) {
        return claseRepository.save(clase);
    }

    @Override
    public Clase guardar(Clase clase) {
        return claseRepository.save(clase);
    }

    @Override
    public Clase actualizar(Long id, Clase clase) {
        Clase updated = Clase.builder()
                .id(id)
                .nombre(clase.getNombre())
                .maestro(clase.getMaestro())
                .build();
        return claseRepository.save(updated);
    }

    @Override
    public void eliminar(Long id) {
        Clase clase = claseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Clase no encontrada"));

        if (!clase.getAlumnos().isEmpty()) {
            throw new RuntimeException("No se puede eliminar la clase porque tiene alumnos inscritos");
        }

        claseRepository.deleteById(id);
    }

    @Override
    public List<Alumno> obtenerAlumnosDeClase(Long claseId) {
        return List.copyOf(obtenerPorId(claseId).getAlumnos());
    }

    @Override
    @Transactional
    public void agregarAlumno(Long claseId, Long alumnoId) {
        Clase clase = obtenerPorId(claseId);
        Alumno alumno = alumnoRepository.findById(alumnoId)
                .orElseThrow(() -> new EntityNotFoundException("Alumno no encontrado"));
        clase.getAlumnos().add(alumno);
        alumno.getClases().add(clase);
    }

    @Override
    @Transactional
    public void quitarAlumno(Long claseId, Long alumnoId) {
        Clase clase = obtenerPorId(claseId);
        Alumno alumno = alumnoRepository.findById(alumnoId)
                .orElseThrow(() -> new EntityNotFoundException("Alumno no encontrado"));

        clase.getAlumnos().remove(alumno);
        alumno.getClases().remove(clase);
    }

    @Override
    public List<Alumno> buscarAlumnosDisponibles(Long claseId, String nombre) {
        Clase clase = claseRepository.findById(claseId)
                .orElseThrow(() -> new EntityNotFoundException("Clase no encontrada"));

        List<Alumno> alumnos;

        if (nombre == null || nombre.isBlank()) {
            alumnos = alumnoRepository.findAll();
        } else {
            alumnos = alumnoRepository.findByNombreContainingIgnoreCase(nombre);
        }

        return alumnos.stream()
                .filter(a -> !clase.getAlumnos().contains(a))
                .toList();
    }
}
