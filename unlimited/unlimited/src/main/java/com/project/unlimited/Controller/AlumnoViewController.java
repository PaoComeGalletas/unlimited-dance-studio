package com.project.unlimited.Controller;

import com.project.unlimited.Model.Alumno;
import com.project.unlimited.Service.AlumnoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/alumnos")
public class AlumnoViewController {

    private final AlumnoService alumnoService;

    public AlumnoViewController(AlumnoService alumnoService) {
        this.alumnoService = alumnoService;
    }

    // 📋 Lista
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("alumnos", alumnoService.obtenerTodos());
        return "alumnos/lista";
    }

    // ➕ Formulario crear
    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("alumno", Alumno.builder().build());
        return "alumnos/formulario";
    }

    // 💾 Guardar
    @PostMapping
    public String guardar(@ModelAttribute Alumno alumno) {
        alumnoService.crear(alumno);
        return "redirect:/alumnos";
    }

    // ✏️ Editar
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("alumno", alumnoService.obtenerPorId(id));
        return "alumnos/formulario";
    }

    // ❌ Eliminar
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        alumnoService.eliminar(id);
        return "redirect:/alumnos";
    }
}
