package com.project.unlimited.Controller;

import com.project.unlimited.Model.Alumno;
import com.project.unlimited.Service.AlumnoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/alumnos")
public class AlumnoController {

    private final AlumnoService alumnoService;

    public AlumnoController(AlumnoService alumnoService) {
        this.alumnoService = alumnoService;
    }

    //GET /api/alumnos
    @GetMapping
    public ResponseEntity<List<Alumno>> getAll() {
        return ResponseEntity.ok(alumnoService.obtenerTodos());
    }

    // GET /api/alumnos/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Alumno> getById(@PathVariable Long id) {
        return ResponseEntity.ok(alumnoService.obtenerPorId(id));
    }

    //POST /api/alumnos/
    @PostMapping
    public ResponseEntity<Alumno> create(@RequestBody Alumno alumno) {
        Alumno create = alumnoService.crear(alumno);
        return ResponseEntity.status(HttpStatus.CREATED).body(create);
    }

    // PUT /api/alumnos/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Alumno> update(@PathVariable Long id, @RequestBody Alumno alumno) {
        return ResponseEntity.ok(alumnoService.actualizar(id, alumno));
    }

    public ResponseEntity<Void> remove(@PathVariable Long id) {
        alumnoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
