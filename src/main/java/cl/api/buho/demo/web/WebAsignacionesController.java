package cl.api.buho.demo.web;

import cl.api.buho.demo.models.Asignaciones;
import cl.api.buho.demo.services.AsignacionesService;
import cl.api.buho.demo.services.DesarrolladoresService;
import cl.api.buho.demo.services.SolicitudesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/asignaciones")
public class WebAsignacionesController {

    @Autowired
    private AsignacionesService asignacionesService;

    @Autowired
    private SolicitudesService solicitudesService;

    @Autowired
    private DesarrolladoresService desarrolladoresService;

    // Listar todas
    @GetMapping
    public String listarAsignaciones(Model model) {
        List<Asignaciones> asignaciones = asignacionesService.listarAsignaciones();
        model.addAttribute("asignaciones", asignaciones);
        return "web-asignacion/listar";
    }

    // Buscar por ID
    @GetMapping("/buscar")
    public String buscarPorId(@RequestParam("id") Long id, Model model) {
        Optional<Asignaciones> asignacion = asignacionesService.obtenerAsignacionPorId(id);
        asignacion.ifPresent(a -> model.addAttribute("asignaciones", List.of(a)));
        return "web-asignacion/listar";
    }

    // Eliminar
    @GetMapping("/eliminar/{id}")
    public String eliminarAsignacion(@PathVariable Long id) {
        asignacionesService.eliminarAsignacion(id);
        return "redirect:/asignaciones";
    }

    // Mostrar formulario de edición
    @GetMapping("/editar/{id}")
    public String editarAsignacion(@PathVariable Long id, Model model) {
        Optional<Asignaciones> asignacion = asignacionesService.obtenerAsignacionPorId(id);
        if (asignacion.isPresent()) {
            model.addAttribute("asignacionObj", asignacion.get());
            model.addAttribute("solicitudesLista", solicitudesService.listarSolicitudes());
            model.addAttribute("desarrolladoresLista", desarrolladoresService.listarDesarrolladores());
            return "web-asignacion/editar";
        }
        return "redirect:/asignaciones";
    }

    // Procesar edición
    @PostMapping("/editar/{id}")
    public String actualizarAsignacion(@PathVariable Long id, @ModelAttribute("asignacionObj") Asignaciones asignacion) {
        asignacionesService.actualizarAsignacion(id, asignacion);
        return "redirect:/asignaciones";
    }

    // Mostrar formulario para nueva asignación
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("asignacionObj", new Asignaciones());
        model.addAttribute("solicitudesLista", solicitudesService.listarSolicitudes());
        model.addAttribute("desarrolladoresLista", desarrolladoresService.listarDesarrolladores());
        return "web-asignacion/nuevo";
    }

    // Guardar nueva asignación
    @PostMapping("/guardar")
    public String guardarNuevaAsignacion(@ModelAttribute("asignacionObj") Asignaciones asignacion) {
        asignacionesService.crearAsignacion(asignacion);
        return "redirect:/asignaciones";
    }
}
