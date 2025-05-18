package cl.api.buho.demo.web;

import cl.api.buho.demo.models.NotificacionesDesarrolladores;
import cl.api.buho.demo.services.AsignacionesService;
import cl.api.buho.demo.services.NotificacionesDesarrolladoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/notificaciones-desarrolladores")
public class WebNotificacionesDesarrolladoresController {

    @Autowired
    private NotificacionesDesarrolladoresService notificacionesDesarrolladoresService;

    @Autowired
    private AsignacionesService asignacionesService;

    // Listar todos
    @GetMapping
    public String listarNotificacionesDesarrolladores(Model model) {
        List<NotificacionesDesarrolladores> notificaciones = notificacionesDesarrolladoresService.listarNotificacionesDesarrolladores();
        model.addAttribute("notificaciones", notificaciones);
        return "web-notificacion-desarrollador/listar";
    }

    // Buscar por ID
    @GetMapping("/buscar")
    public String buscarPorId(@RequestParam("id") Long id, Model model) {
        Optional<NotificacionesDesarrolladores> notificacion = notificacionesDesarrolladoresService.obtenerNotificacionDesarrolladorPorId(id);
        notificacion.ifPresent(n -> model.addAttribute("notificaciones", List.of(n)));
        return "web-notificacion-desarrollador/listar";
    }

    // Eliminar
    @GetMapping("/eliminar/{id}")
    public String eliminarNotificacionDesarrollador(@PathVariable Long id) {
        notificacionesDesarrolladoresService.eliminarNotificacionDesarrollador(id);
        return "redirect:/notificaciones-desarrolladores";
    }

    // Mostrar formulario de edición
    @GetMapping("/editar/{id}")
    public String editarNotificacionDesarrollador(@PathVariable Long id, Model model) {
        Optional<NotificacionesDesarrolladores> notificacion = notificacionesDesarrolladoresService.obtenerNotificacionDesarrolladorPorId(id);
        if (notificacion.isPresent()) {
            model.addAttribute("notificacionObj", notificacion.get());
            model.addAttribute("asignacionesLista", asignacionesService.listarAsignaciones());
            return "web-notificacion-desarrollador/editar";
        }
        return "redirect:/notificaciones-desarrolladores";
    }

    // Procesar edición
    @PostMapping("/editar/{id}")
    public String actualizarNotificacionDesarrollador(@PathVariable Long id, @ModelAttribute("notificacionObj") NotificacionesDesarrolladores notificacion) {
        notificacionesDesarrolladoresService.actualizarNotificacionDesarrollador(id, notificacion);
        return "redirect:/notificaciones-desarrolladores";
    }

    // Mostrar formulario para crear nueva notificación
    @GetMapping("/nueva")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("notificacionObj", new NotificacionesDesarrolladores());
        model.addAttribute("asignacionesLista", asignacionesService.listarAsignaciones());
        return "web-notificacion-desarrollador/nuevo";
    }

    // Guardar nueva notificación
    @PostMapping("/guardar")
    public String guardarNuevaNotificacion(@ModelAttribute("notificacionObj") NotificacionesDesarrolladores notificacion) {
        notificacionesDesarrolladoresService.crearNotificacionDesarrollador(notificacion);
        return "redirect:/notificaciones-desarrolladores";
    }
}
