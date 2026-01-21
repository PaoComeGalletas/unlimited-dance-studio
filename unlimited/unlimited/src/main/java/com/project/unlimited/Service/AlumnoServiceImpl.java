package com.project.unlimited.Service;

import com.project.unlimited.Model.Alumno;
import com.project.unlimited.Repository.AlumnoRepository;
import com.project.unlimited.Repository.ClaseRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AlumnoServiceImpl implements AlumnoService{

    private final AlumnoRepository alumnoRepository;

    public AlumnoServiceImpl(AlumnoRepository alumnoRepository) {
        this.alumnoRepository = alumnoRepository;
    }

    @Override
    public List<Alumno> obtenerTodos() {
        return alumnoRepository.findAll();
    }

    @Override
    public Alumno obtenerPorId(Long id) {
        return alumnoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Alumno no encontrado"));
    }

    @Override
    public Alumno crear(Alumno alumno) {
        return alumnoRepository.save(alumno);
    }

    @Override
    public Alumno actualizar(Long id, Alumno alumno) {
        Alumno existent = obtenerPorId(id);
        Alumno updated = Alumno.builder()
                .id(existent.getId())
                .nombre(alumno.getNombre())
                .diaPago(alumno.getDiaPago())
                .mensualidad(alumno.getMensualidad())
                .contactoEmergencia(alumno.getContactoEmergencia())
                .telefonoEmergencia(alumno.getTelefonoEmergencia())
                .clases(alumno.getClases())
                .build();
        return alumnoRepository.save(updated);
    }

    @Override
    public void eliminar(Long id) {
        alumnoRepository.deleteById(id);
    }
}
