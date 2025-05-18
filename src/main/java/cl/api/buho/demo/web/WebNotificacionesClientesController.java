package cl.api.buho.demo.web;

import cl.api.buho.demo.models.NotificacionesClientes;
import cl.api.buho.demo.services.NotificacionesClientesService;
import cl.api.buho.demo.services.SolicitudesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/notificaciones-clientes")
public class WebNotificacionesClientesController {

    @Autowired
    private NotificacionesClientesService notificacionesClientesService;

    @Autowired
    private SolicitudesService solicitudesService;

    // Listar todos
    @GetMapping
    public String listarNotificacionesClientes(Model model) {
        List<NotificacionesClientes> notificaciones = notificacionesClientesService.listarNotificacionesClientes();
        model.addAttribute("notificaciones", notificaciones);
        return "web-notificacion-cliente/listar";
    }

    // Buscar por ID
    @GetMapping("/buscar")
    public String buscarPorId(@RequestParam("id") Long id, Model model) {
        Optional<NotificacionesClientes> notificacion = notificacionesClientesService.obtenerNotificacionClientePorId(id);
        notificacion.ifPresent(n -> model.addAttribute("notificaciones", List.of(n)));
        return "web-notificacion-cliente/listar";
    }

    // Eliminar
    @GetMapping("/eliminar/{id}")
    public String eliminarNotificacionCliente(@PathVariable Long id) {
        notificacionesClientesService.eliminarNotificacionCliente(id);
        return "redirect:/notificaciones-clientes";
    }

    // Mostrar formulario de edición
    @GetMapping("/editar/{id}")
    public String editarNotificacionCliente(@PathVariable Long id, Model model) {
        Optional<NotificacionesClientes> notificacion = notificacionesClientesService.obtenerNotificacionClientePorId(id);
        if (notificacion.isPresent()) {
            model.addAttribute("notificacionObj", notificacion.get());
            model.addAttribute("solicitudesLista", solicitudesService.listarSolicitudes());
            return "web-notificacion-cliente/editar";
        }
        return "redirect:/notificaciones-clientes";
    }

    // Procesar edición
    @PostMapping("/editar/{id}")
    public String actualizarNotificacionCliente(@PathVariable Long id, @ModelAttribute("notificacionObj") NotificacionesClientes notificacion) {
        notificacionesClientesService.actualizarNotificacionCliente(id, notificacion);
        return "redirect:/notificaciones-clientes";
    }

    // Mostrar formulario para crear nueva notificación
    @GetMapping("/nueva")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("notificacionObj", new NotificacionesClientes());
        model.addAttribute("solicitudesLista", solicitudesService.listarSolicitudes());
        return "web-notificacion-cliente/nuevo";
    }

    // Guardar nueva notificación
    @PostMapping("/guardar")
    public String guardarNuevaNotificacion(@ModelAttribute("notificacionObj") NotificacionesClientes notificacion) {
        notificacionesClientesService.crearNotificacionCliente(notificacion);
        return "redirect:/notificaciones-clientes";
    }
}

