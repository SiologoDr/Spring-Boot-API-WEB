package cl.api.buho.demo.web;

import cl.api.buho.demo.models.Solicitudes;
import cl.api.buho.demo.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/solicitudes")
public class WebSolicitudesController {

    @Autowired
    private SolicitudesService solicitudesService;

    @Autowired
    private ClientesService clientesService;

    @Autowired
    private TecnicosService tecnicosService;

    @Autowired
    private EstadosService estadosService;

    @Autowired
    private RevisionesService revisionesService;

    // Listar todos
    @GetMapping
    public String listarSolicitudes(Model model) {
        List<Solicitudes> solicitudes = solicitudesService.listarSolicitudes();
        model.addAttribute("solicitudes", solicitudes);
        return "web-solicitud/listar";
    }

    // Buscar por ID
    @GetMapping("/buscar")
    public String buscarPorId(@RequestParam("id") Long id, Model model) {
        Optional<Solicitudes> solicitud = solicitudesService.obtenerSolicitudPorId(id);
        solicitud.ifPresent(s -> model.addAttribute("solicitudes", List.of(s)));
        return "web-solicitud/listar";
    }

    // Eliminar
    @GetMapping("/eliminar/{id}")
    public String eliminarSolicitud(@PathVariable Long id) {
        solicitudesService.eliminarSolicitud(id);
        return "redirect:/solicitudes";
    }

    // Mostrar formulario de edición
    @GetMapping("/editar/{id}")
    public String editarSolicitud(@PathVariable Long id, Model model) {
        Optional<Solicitudes> solicitud = solicitudesService.obtenerSolicitudPorId(id);
        if (solicitud.isPresent()) {
            model.addAttribute("solicitudObj", solicitud.get());
            model.addAttribute("clientesLista", clientesService.listarClientes());
            model.addAttribute("tecnicosLista", tecnicosService.listarTecnicos());
            model.addAttribute("estadosLista", estadosService.listarEstados());
            model.addAttribute("revisionesLista", revisionesService.listarRevisiones());
            return "web-solicitud/editar";
        }
        return "redirect:/solicitudes";
    }

    // Procesar edición
    @PostMapping("/editar/{id}")
    public String actualizarSolicitud(@PathVariable Long id, @ModelAttribute("solicitudObj") Solicitudes solicitud) {
        solicitudesService.actualizarSolicitud(id, solicitud);
        return "redirect:/solicitudes";
    }

    // Mostrar formulario para nueva solicitud
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("solicitudObj", new Solicitudes());
        model.addAttribute("clientesLista", clientesService.listarClientes());
        model.addAttribute("tecnicosLista", tecnicosService.listarTecnicos());
        model.addAttribute("estadosLista", estadosService.listarEstados());
        model.addAttribute("revisionesLista", revisionesService.listarRevisiones());
        return "web-solicitud/nuevo";
    }

    // Guardar nueva solicitud
    @PostMapping("/guardar")
    public String guardarNuevaSolicitud(@ModelAttribute("solicitudObj") Solicitudes solicitud) {
        solicitudesService.crearSolicitud(solicitud);
        return "redirect:/solicitudes";
    }
}

