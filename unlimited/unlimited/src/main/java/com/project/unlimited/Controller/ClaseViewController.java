package com.project.unlimited.Controller;

import com.project.unlimited.Model.Clase;
import com.project.unlimited.Service.ClaseService;
import com.project.unlimited.Service.AlumnoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/clases")
public class ClaseViewController {

    private final ClaseService claseService;
    private final AlumnoService alumnoService;

    public ClaseViewController(ClaseService claseService, AlumnoService alumnoService) {
        this.claseService = claseService;
        this.alumnoService = alumnoService;
    }

    // 📋 Lista de clases
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("clases", claseService.obtenerTodas());
        return "clases/lista";
    }

    // ➕ Formulario nueva clase
    @GetMapping("/nueva")
    public String nueva(Model model) {
        model.addAttribute("clase", new Clase());
        return "clases/formularioNuevaClase";
    }

    // Editar unicamente los atributos de una clase
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Clase clase = claseService.obtenerPorId(id);
        model.addAttribute("clase", clase);
        return "clases/formularioNuevaClase";
    }

    // Eliminar una clase existente
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            claseService.eliminar(id);
            redirectAttributes.addFlashAttribute("success", "Clase eliminada correctamente");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }

        return "redirect:/clases";
    }

    // 💾 Guardar
    @PostMapping
    public String guardar(@ModelAttribute Clase clase) {
        claseService.guardar(clase);
        return "redirect:/clases";
    }

    // 🔍 Detalle de clase 👑
    @GetMapping("/{id}")
    public String detalle(@PathVariable Long id, Model model) {
        model.addAttribute("clase", claseService.obtenerPorId(id));
        model.addAttribute("alumnos", claseService.obtenerAlumnosDeClase(id));
        model.addAttribute("todosAlumnos", alumnoService.obtenerTodos());
        return "clases/detalle";
    }

    // ➕ Agregar alumno
    @PostMapping("/{claseId}/agregar-alumno")
    public String agregarAlumno(
            @PathVariable Long claseId,
            @RequestParam Long alumnoId) {

        claseService.agregarAlumno(claseId, alumnoId);
        return "redirect:/clases/" + claseId;
    }

    // ➖ Quitar alumno
    @GetMapping("/{claseId}/quitar-alumno/{alumnoId}")
    public String quitarAlumno(
            @PathVariable Long claseId,
            @PathVariable Long alumnoId) {

        claseService.quitarAlumno(claseId, alumnoId);
        return "redirect:/clases/" + claseId;
    }
}
