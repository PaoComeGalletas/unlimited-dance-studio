package com.project.unlimited.Repository;

import com.project.unlimited.Model.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlumnoRepository extends JpaRepository<Alumno, Long> {
    List<Alumno> findByNombreContainingIgnoreCase(String nombre);
}
