package com.project.unlimited.Controller;

import com.project.unlimited.Model.Alumno;
import com.project.unlimited.Model.Clase;
import com.project.unlimited.Service.ClaseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clases")
public class ClaseController {

    private final ClaseService claseService;

    public ClaseController(ClaseService claseService) {
        this.claseService = claseService;
    }

    //GET /api/clases
    @GetMapping
    public ResponseEntity<List<Clase>> getAll() {
        return ResponseEntity.ok(claseService.obtenerTodas());
    }

    //GET /api/clases/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Clase> getById(@PathVariable Long id) {
        return ResponseEntity.ok(claseService.obtenerPorId(id));
    }

    //POST /api/clases
    @PostMapping
    public ResponseEntity<Clase> create(@RequestBody Clase clase) {
        Clase create = claseService.crear(clase);
        return ResponseEntity.status(HttpStatus.CREATED).body(create);
    }

    //PUT /api/clases/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Clase> update(@PathVariable Long id, @RequestBody Clase clase) {
        return ResponseEntity.ok(claseService.actualizar(id, clase));
    }

    //DELETE /api/clases/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        claseService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    // Ver alumnos de una clase
    //GET /api/clases/{id}/alumnos
    @GetMapping("/{id}/alumnos")
    public ResponseEntity<List<Alumno>> getStudents(@PathVariable Long id) {
        return ResponseEntity.ok(claseService.obtenerAlumnosDeClase(id));
    }

    //  Agregar alumno a clase
    // POST /api/clases/{claseId}/alumnos/{alumnoId}
    @PostMapping("/{claseId}/alumnos/{alumnoId}")
    public ResponseEntity<Void> agregarAlumno(@PathVariable Long claseId, @PathVariable Long alumnoId) {
        claseService.agregarAlumno(claseId, alumnoId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    //Quitar alumno de clase
    // DELETE /api/clases/{claseId}/alumnos/{alumnoId}
    @DeleteMapping("/{claseId}/alumnos/{alumnoId}")
    public ResponseEntity<Void> quitarAlumno( @PathVariable Long claseId, @PathVariable Long alumnoId) {
        claseService.quitarAlumno(claseId, alumnoId);
        return ResponseEntity.noContent().build();
    }
}
